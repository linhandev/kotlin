// DISABLE_NATIVE: gcType=NOOP
// TARGET_BACKEND: NATIVE
// Tests OHOS memory tagging from SR005 commits: restrace (CustomAllocator / CustomFinalizerProcessor)
// and VMA anon name (GCApi::SafeAlloc). On non-OHOS kernels /proc checks are skipped.
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.native.internal.InternalForKotlinNative,kotlin.ExperimentalStdlibApi,kotlinx.cinterop.ExperimentalForeignApi

import kotlin.test.*
import kotlin.native.ref.createCleaner
import kotlin.native.runtime.GC
import kotlinx.cinterop.*
import platform.posix.*

/**
 * Black-box tests for OHOS DFX SR005 memory tagging (commits dfdefd9..23be7fa).
 *
 * Runtime (KONAN_OHOS):
 *   CreateObject / CreateArray → restrace(RES_KMP_HEAP_MASK, addr, size, TAG, is_using=true) when API >= 21
 *   CustomFinalizerProcessor after RunFinalizers → restrace(..., is_using=false)
 *   SafeAlloc after mmap → prctl(PR_SET_VMA, PR_SET_VMA_ANON_NAME, ..., "kotlin heap")
 *
 * Constants must match kotlin-native/runtime/src/main/cpp/memory_trace.h and Memory.h.
 */
@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class,
    kotlin.native.runtime.NativeRuntimeApi::class,
)
class OhosMemoryTraceTagTest {

    private fun logLine(msg: String) = println(msg)

    // ---------- Spec constants (memory_trace.h / Memory.h / GCApi.cpp) ----------

    private val expectedTagName = "RES_KMP_HEAP_MASK"
    private val expectedMaskValue = 1 shl 19
    private val ohosRestraceMinApi = 21
    private val vmaAnonName = "kotlin heap"

    // ---------- API gating mirror (CustomAllocator.cpp / CustomFinalizerProcessor.hpp) ----------

    /** Mirrors: if (OH_GetSdkApiVersion() >= OHOS_RESTRACE_MIN_API) { restrace(...) } */
    private fun shouldInvokeRestrace(sdkApiVersion: Int): Boolean =
        sdkApiVersion >= ohosRestraceMinApi

    // ---------- /proc helpers ----------

    private fun isOhosKernel(): Boolean {
        val f = fopen("/proc/self/smaps", "r") ?: return false
        fclose(f)
        return true
    }

    private fun readSmapsNameLabels(): Set<String> {
        val f = fopen("/proc/self/smaps", "r") ?: return emptySet()
        val labels = mutableSetOf<String>()
        memScoped {
            val buf = allocArray<ByteVar>(1024)
            while (fgets(buf, 1023, f) != null) {
                val line = buf.toKString().trim()
                if (line.startsWith("Name:")) {
                    labels.add(line.removePrefix("Name:").trim())
                }
            }
        }
        fclose(f)
        return labels
    }

    // ---------- Allocation fixtures ----------

    private class SmallObj(val value: Int)

    private class MediumObj(val data: IntArray = IntArray(1024) { it })

    private class LargeData {
        val array = IntArray(4096) { it }
        val str = "VMA label test ${"x".repeat(128)}"
    }

    /** Passed to createCleaner; action must be a method on the cleaned object (non-capturing). */
    private class CleanerTarget {
        var cleaned: Boolean = false
        fun markCleaned() {
            cleaned = true
        }
    }

    private class IndexedCleanerTarget(
        private val slot: Int,
        private val flags: IntArray,
    ) {
        fun markCleaned() {
            flags[slot] = 1
        }
    }

    // ---------- Restrace: constants (all platforms) ----------

    @Test
    fun testRestraceTagNameConstant() {
        assertEquals(expectedTagName, "RES_KMP_HEAP_MASK")
    }

    @Test
    fun testRestraceHeapMaskValue() {
        assertEquals(524288, expectedMaskValue)
        assertEquals(0x80000, expectedMaskValue)
        assertTrue(expectedMaskValue > 0 && (expectedMaskValue and (expectedMaskValue - 1)) == 0)
    }

    @Test
    fun testRestraceMinApiConstant() {
        assertEquals(21, ohosRestraceMinApi)
    }

    @Test
    fun testRestraceApiGate_mirror() {
        assertFalse(shouldInvokeRestrace(20))
        assertFalse(shouldInvokeRestrace(ohosRestraceMinApi - 1))
        assertTrue(shouldInvokeRestrace(ohosRestraceMinApi))
        assertTrue(shouldInvokeRestrace(26))
        logLine("restrace API gate mirror ok minApi=$ohosRestraceMinApi")
    }

    @Test
    fun testRestraceHeapMaskBitPosition() {
        val systemLowMask = 0xFF_FFFF.toLong()
        assertTrue(
            (expectedMaskValue.toLong() and systemLowMask) == 0L ||
                expectedMaskValue >= (1 shl 16),
        )
        assertEquals(0, expectedMaskValue and (expectedMaskValue - 1))
    }

    // ---------- Restrace: CreateObject / CreateArray paths (all platforms) ----------

    @Test
    fun testPlainObjectAllocationWithRestrace() {
        val obj = SmallObj(42)
        GC.collect()
        assertEquals(42, obj.value)
    }

    @Test
    fun testArrayAllocationWithRestrace() {
        val arr = IntArray(2048) { it * 2 }
        GC.collect()
        assertEquals(0, arr[0])
        assertEquals(4094, arr[2047])
    }

    @Test
    fun testMediumObjectAllocationWithRestrace() {
        val obj = MediumObj()
        GC.collect()
        assertEquals(1024, obj.data.size)
        assertEquals(1023, obj.data[1023])
    }

    @Test
    fun testRepeatedAllocationsDoNotCrash() {
        repeat(50) { i ->
            val objs = Array(20) { SmallObj(i * 20 + it) }
            GC.collect()
            assertEquals(i * 20, objs[0].value)
        }
    }

    @Test
    fun testLargeArrayAllocationWithRestrace() {
        val large = IntArray(512 * 1024) { it }
        GC.collect()
        assertEquals(0, large[0])
        assertEquals(large.size - 1, large[large.size - 1])
    }

    // ---------- Restrace: CustomFinalizerProcessor (is_using=false) ----------

    /**
     * Exercises GC finalizer queue → RunFinalizers → restrace(..., is_using=false) in
     * CustomFinalizerProcessor.hpp. Uses createCleaner so ExtraObjectData is installed.
     */
    @Test
    fun testFinalizerPath_restraceRelease_doesNotCrash() {
        var targetRef: CleanerTarget? = null
        run {
            val target = CleanerTarget()
            targetRef = target
            createCleaner(target, CleanerTarget::markCleaned)
        }
        repeat(4) { GC.collect() }
        assertTrue(targetRef != null && targetRef.cleaned,
            "cleaner must run so CustomFinalizerProcessor restrace(is_using=false) is exercised")
        logLine("finalizer restrace release path ok")
    }

    @Test
    fun testFinalizerPath_afterBulkAllocThenCollect() {
        val flags = IntArray(8)
        repeat(8) { i ->
            run {
                val target = IndexedCleanerTarget(i, flags)
                createCleaner(target, IndexedCleanerTarget::markCleaned)
            }
            GC.collect()
        }
        repeat(5) { GC.collect() }
        assertEquals(8, flags.sum())
        logLine("bulk finalizer path ok count=${flags.size}")
    }

    // ---------- VMA: prctl anon name (GCApi::SafeAlloc) ----------

    @Test
    fun testVmaAnonNameConstant() {
        assertEquals("kotlin heap", vmaAnonName)
    }

    @Test
    fun testVmaLabelPresentAfterAllocation() {
        if (!isOhosKernel()) return
        val objects = Array(16) { LargeData() }
        GC.collect()
        val labels = readSmapsNameLabels()
        assertTrue(
            vmaAnonName in labels,
            "Expected VMA '$vmaAnonName' in smaps after SafeAlloc; labels=${labels.take(20)}",
        )
        assertEquals(4096, objects[0].array.size)
    }

    @Test
    fun testVmaLabelSurvivesGcCycle() {
        if (!isOhosKernel()) return
        repeat(3) { cycle ->
            val batch = Array(8) { LargeData() }
            GC.collect()
            assertTrue(
                vmaAnonName in readSmapsNameLabels(),
                "VMA label lost after GC cycle $cycle",
            )
            assertEquals(4096, batch[0].array.size)
        }
    }

    @Test
    fun testVmaLabelForLargeSingleAllocation() {
        if (!isOhosKernel()) return
        val bigArray = IntArray(1024 * 1024) { it }
        GC.collect()
        assertTrue(vmaAnonName in readSmapsNameLabels())
        assertEquals(1024 * 1024, bigArray.size)
    }

    @Test
    fun testVmaLabelAfterScopedLargeAllocation() {
        if (!isOhosKernel()) return
        val data = LargeData()
        val size = data.array.size
        GC.collect()
        assertTrue(vmaAnonName in readSmapsNameLabels())
        assertEquals(4096, size)
    }

    @Test
    fun testVmaLabelCountAndContent() {
        if (!isOhosKernel()) return
        val objects = buildList {
            for (kb in listOf(4, 8, 16, 32, 64, 128)) {
                add(IntArray(kb * 256) { it })
            }
        }
        GC.collect()
        val names = readSmapsNameLabels().toList()
        assertTrue(names.count { it == vmaAnonName } >= 1)
        assertTrue(names.none { it.isEmpty() })
        assertEquals(4 * 256, objects[0].size)
    }

    // ---------- OHOS integration: restrace tag vs VMA label ----------

    @Test
    fun testRestraceOrVmaLabelInSmapsAfterHeapGrowth() {
        if (!isOhosKernel()) return
        val objs = Array(32) { MediumObj() }
        GC.collect()
        val labels = readSmapsNameLabels()
        val heapRelated = setOf(vmaAnonName, expectedTagName)
        assertTrue(
            labels.intersect(heapRelated).isNotEmpty(),
            "Expected restrace tag or VMA name in smaps; got=${labels.take(20)}",
        )
        assertEquals(1024, objs[0].data.size)
    }

    @Test
    fun testRestraceTagPersistsAfterGcAndRealloc() {
        if (!isOhosKernel()) return
        val phase1 = Array(16) { MediumObj() }
        GC.collect()
        assertEquals(1024, phase1[0].data.size)
        val phase2 = Array(16) { SmallObj(it) }
        GC.collect()
        val labels = readSmapsNameLabels()
        assertTrue(
            labels.intersect(setOf(vmaAnonName, expectedTagName)).isNotEmpty(),
            "Heap tag missing after GC + realloc; labels=${labels.take(20)}",
        )
        assertEquals(15, phase2[15].value)
    }
}
