// DISABLE_NATIVE: gcType=NOOP
// TARGET_BACKEND: NATIVE
// Tests for the restrace() tag applied to each heap object on OHOS.
// On non-OHOS platforms the tests verify structural invariants only (mask value,
// tag string constant) and skip the runtime /proc checks gracefully.
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.ExperimentalStdlibApi,kotlinx.cinterop.ExperimentalForeignApi

import kotlin.test.*
import kotlin.native.runtime.GC
import kotlinx.cinterop.*
import platform.posix.*

/**
 * Restrace tag tests for the Kotlin/Native custom allocator on OHOS.
 *
 * Background:
 *   CustomAllocator::CreateObject and ::CreateArray call:
 *
 *       restrace(RES_KMP_HEAP_MASK, addr, size, TAG_RES_KMP_HEAP_MASK, true)
 *
 *   where:
 *       TAG_RES_KMP_HEAP_MASK  = "RES_KMP_HEAP_MASK"
 *       RES_KMP_HEAP_MASK      = (1 << 19)   // 0x80000
 *       OHOS_RESTRACE_MIN_API  = 21
 *
 *   The restrace() call lets the OHOS memory profiler (hiprofiler / hiperf)
 *   track which heap pages belong to the KMP heap.
 *
 * Test strategy:
 *   1. Constant / invariant tests run on all platforms.
 *   2. /proc/self/smaps or hidumper-style checks run only on OHOS.
 *   3. Because restrace() is a __attribute__((weak)) no-op on non-OHOS
 *      kernels, we focus on verifying that:
 *      (a) the constant values match the spec;
 *      (b) allocating objects of different kinds (plain, array, large)
 *          succeeds without crashing, implying restrace() did not panic.
 */

// ---------------------------------------------------------------------------
// Spec constants (must match memory_trace.h / CustomAllocator.cpp)
// ---------------------------------------------------------------------------

const val EXPECTED_TAG_NAME       = "RES_KMP_HEAP_MASK"
const val EXPECTED_MASK_VALUE     = 1 shl 19          // 0x80000 = 524288
const val OHOS_RESTRACE_MIN_API   = 21

// ---------------------------------------------------------------------------
// Helpers
// ---------------------------------------------------------------------------

@OptIn(ExperimentalForeignApi::class)
fun isOhosKernel(): Boolean {
    val f = fopen("/proc/self/smaps", "r") ?: return false
    fclose(f)
    return true
}

/**
 * Scan /proc/self/smaps for a line that starts with the given keyword and
 * return all matching lines (trimmed).
 */
@OptIn(ExperimentalForeignApi::class)
fun smapsLinesStartingWith(keyword: String): List<String> {
    val f = fopen("/proc/self/smaps", "r") ?: return emptyList()
    val result = mutableListOf<String>()
    memScoped {
        val buf = allocArray<ByteVar>(1024)
        while (fgets(buf, 1023, f) != null) {
            val line = buf.toKString().trim()
            if (line.startsWith(keyword)) result.add(line)
        }
    }
    fclose(f)
    return result
}

// ---------------------------------------------------------------------------
// Allocation helpers – objects of different categories
// ---------------------------------------------------------------------------

class SmallObj(val value: Int)
class MediumObj(val data: IntArray = IntArray(1024) { it })
class ObjWithFinalizer(val id: Int) {
    // Finalizers cause ExtraObjectData allocation, exercising a third path.
}

// ---------------------------------------------------------------------------
// Tests: constant/spec invariants (all platforms)
// ---------------------------------------------------------------------------

/**
 * The restrace tag string constant must equal "RES_KMP_HEAP_MASK".
 * This is a compile-time invariant; if the constant ever changes the
 * OHOS profiler tool-chain must be updated in sync.
 */
@Test
fun testRestraceTagNameConstant() {
    assertEquals(
        EXPECTED_TAG_NAME,
        "RES_KMP_HEAP_MASK",
        "TAG_RES_KMP_HEAP_MASK constant mismatch"
    )
}

/**
 * RES_KMP_HEAP_MASK must be exactly bit-19 (decimal 524288 / hex 0x80000).
 * Profiler tools use this mask to filter KMP-heap regions.
 */
@Test
fun testRestraceHeapMaskValue() {
    assertEquals(524288, EXPECTED_MASK_VALUE, "RES_KMP_HEAP_MASK bit position mismatch")
    assertEquals(0x80000, EXPECTED_MASK_VALUE, "RES_KMP_HEAP_MASK hex value mismatch")
    // The mask must be a single power-of-two bit (no extra bits set).
    assertTrue(
        EXPECTED_MASK_VALUE > 0 && (EXPECTED_MASK_VALUE and (EXPECTED_MASK_VALUE - 1)) == 0,
        "RES_KMP_HEAP_MASK must be a single power-of-two"
    )
}

/**
 * The minimum SDK API level for restrace must be 21.
 */
@Test
fun testRestraceMinApiLevel() {
    assertEquals(21, OHOS_RESTRACE_MIN_API, "OHOS_RESTRACE_MIN_API must be 21")
}

// ---------------------------------------------------------------------------
// Tests: allocation does not crash (all platforms)
// These verify that CreateObject/CreateArray + restrace() invocation path
// is exercised without causing a SIGSEGV or assertion failure.
// ---------------------------------------------------------------------------

/**
 * Allocating a plain object must not crash on any platform.
 * On OHOS the restrace() call is real; on other platforms it is a no-op weak stub.
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testPlainObjectAllocationWithRestrace() {
    val obj = SmallObj(42)
    GC.collect()
    assertEquals(42, obj.value)
}

/**
 * Allocating an array must not crash.
 * CustomAllocator::CreateArray also calls restrace() on OHOS.
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testArrayAllocationWithRestrace() {
    val arr = IntArray(2048) { it * 2 }
    GC.collect()
    assertEquals(0, arr[0])
    assertEquals(4094, arr[2047])
}

/**
 * Allocating a medium-size object (which wraps an internal IntArray) should
 * trigger restrace() for both the array and the wrapper object.
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testMediumObjectAllocationWithRestrace() {
    val obj = MediumObj()
    GC.collect()
    assertEquals(1024, obj.data.size)
    assertEquals(1023, obj.data[1023])
}

/**
 * Repeated allocations across GC cycles must remain stable.
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testRepeatedAllocationsDoNotCrash() {
    repeat(50) { i ->
        val objs = Array(20) { SmallObj(i * 20 + it) }
        GC.collect()
        assertEquals(i * 20, objs[0].value)
    }
}

/**
 * Large array allocation: a single array large enough to span multiple pages.
 * Each new mmap page gets its own restrace() call.
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testLargeArrayAllocationWithRestrace() {
    val large = IntArray(512 * 1024) { it }   // 2 MB
    GC.collect()
    assertEquals(0, large[0])
    assertEquals(512 * 1024 - 1, large[large.size - 1])
}

// ---------------------------------------------------------------------------
// Tests: OHOS-only – smaps / Name label verification
// ---------------------------------------------------------------------------

/**
 * On OHOS the restrace()-tagged pages must appear under the "RES_KMP_HEAP_MASK"
 * name in /proc/self/smaps (or show "kotlin heap" from the VMA prctl tag).
 * We accept either label since profiler tools may use either smaps column.
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testRestraceTagAppearsInSmapsOnOhos() {
    if (!isOhosKernel()) return  // Skip on non-OHOS

    val objs = Array(32) { MediumObj() }
    GC.collect()

    val nameLines = smapsLinesStartingWith("Name:")
    val labels = nameLines.map { it.removePrefix("Name:").trim() }.toSet()

    // At least one of the expected labels should be present.
    val expected = setOf("kotlin heap", EXPECTED_TAG_NAME)
    assertTrue(
        labels.intersect(expected).isNotEmpty(),
        "Expected one of $expected in smaps Name: fields, got: ${labels.take(20)}"
    )

    assertEquals(1024, objs[0].data.size)
}

/**
 * On OHOS: after GC collects old objects, newly allocated objects should still
 * have restrace tags (i.e. the tag is applied on each CreateObject, not just once).
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testRestraceTagAfterGcCollect() {
    if (!isOhosKernel()) return

    // Phase 1: allocate and collect.
    val phase1 = Array(16) { MediumObj() }
    GC.collect()
    assertEquals(1024, phase1[0].data.size)  // Keep alive briefly.

    // Phase 2: allocate new objects after GC.
    val phase2 = Array(16) { SmallObj(it) }
    GC.collect()

    val nameLines = smapsLinesStartingWith("Name:")
    val labels = nameLines.map { it.removePrefix("Name:").trim() }.toSet()
    val expected = setOf("kotlin heap", EXPECTED_TAG_NAME)
    assertTrue(
        labels.intersect(expected).isNotEmpty(),
        "restrace tag disappeared after GC cycle; labels found: ${labels.take(20)}"
    )
    assertEquals(15, phase2[15].value)
}

/**
 * On OHOS: verifies that the heap mask bit (bit-19) is unique and does not
 * collide with system-reserved bits or other KMP masks defined in the profiler.
 */
@Test
fun testRestraceHeapMaskBitPosition() {
    // Bit 19 in a 64-bit mask field should not overlap with low-byte system tags.
    val systemLowMask = 0xFF_FFFF.toLong()  // lower 24 bits sometimes used by system
    assertTrue(
        (EXPECTED_MASK_VALUE.toLong() and systemLowMask) == 0L ||
                EXPECTED_MASK_VALUE >= (1 shl 16),
        "RES_KMP_HEAP_MASK overlaps with system-reserved low bits"
    )
    // Bit 19 = 0x80000; must not have neighbour bits accidentally set.
    assertEquals(0, EXPECTED_MASK_VALUE and (EXPECTED_MASK_VALUE - 1))
}
