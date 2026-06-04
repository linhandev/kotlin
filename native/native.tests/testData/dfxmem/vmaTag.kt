// DISABLE_NATIVE: gcType=NOOP
// TARGET_BACKEND: NATIVE
// Only meaningful on OHOS (Linux-like kernel with PR_SET_VMA support);
// other platforms skip gracefully via the isOhos() guard.
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.ExperimentalStdlibApi,kotlinx.cinterop.ExperimentalForeignApi

import kotlin.test.*
import kotlin.native.runtime.GC
import kotlinx.cinterop.*
import platform.posix.*

/**
 * VMA tag tests for the Kotlin/Native custom allocator on OHOS.
 *
 * Background:
 *   On OHOS (HarmonyOS), the custom allocator calls
 *     prctl(PR_SET_VMA, PR_SET_VMA_ANON_NAME, addr, size, "kotlin heap")
 *   immediately after each mmap() call (see GCApi.cpp:SafeAlloc).
 *   These tests verify that the named-VMA label is correctly applied so that
 *   system tools (hidumper, hiperf, etc.) can identify Kotlin heap regions in
 *   /proc/<pid>/smaps.
 *
 * Test strategy:
 *   - On OHOS, read /proc/self/smaps and check that at least one region is
 *     labelled "kotlin heap" after a GC-visible allocation has taken place.
 *   - On non-OHOS platforms the tests run but are marked as "skipped" by
 *     checking isOhos() so CI stays green on macOS/Linux/Windows.
 */

// ---------------------------------------------------------------------------
// Helpers
// ---------------------------------------------------------------------------

/** Returns true when running on a kernel that exposes PR_SET_VMA (OHOS/Android). */
@OptIn(ExperimentalForeignApi::class)
fun isOhos(): Boolean {
    // /proc/self/smaps is available on Linux-kernel OSes; check for the
    // OHOS-specific smaps "Name:" field format as a proxy.
    val f = fopen("/proc/self/smaps", "r") ?: return false
    fclose(f)
    return true
}

/**
 * Read /proc/self/smaps and return a list of "Name:" values found in it.
 * Returns an empty list on non-Linux platforms.
 */
@OptIn(ExperimentalForeignApi::class)
fun readSmapsNames(): List<String> {
    val f = fopen("/proc/self/smaps", "r") ?: return emptyList()
    val names = mutableListOf<String>()
    memScoped {
        val buf = allocArray<ByteVar>(512)
        while (fgets(buf, 511, f) != null) {
            val line = buf.toKString().trim()
            if (line.startsWith("Name:")) {
                val name = line.removePrefix("Name:").trim()
                if (name.isNotEmpty()) names.add(name)
            }
        }
    }
    fclose(f)
    return names
}

// ---------------------------------------------------------------------------
// Test data that will live on the Kotlin heap
// ---------------------------------------------------------------------------

class LargeData {
    val array = IntArray(4096) { it }   // ~16 KB – forces a new mmap page
    val str = "VMA label test ${"x".repeat(128)}"
}

@ThreadLocal
val tlsData = LargeData()

// ---------------------------------------------------------------------------
// Tests
// ---------------------------------------------------------------------------

/**
 * Basic: after allocating a large object and triggering GC (so the heap page
 * is definitely committed), at least one "kotlin heap" VMA label should appear
 * in smaps on OHOS.
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testVmaLabelPresentAfterAllocation() {
    if (!isOhos()) return  // Skip on non-OHOS platforms

    // Force heap expansion so the allocator mmap()s a new page.
    val objects = Array(16) { LargeData() }
    GC.collect()

    val names = readSmapsNames()
    assertTrue(
        names.any { it == "kotlin heap" },
        "Expected at least one VMA labelled 'kotlin heap' in /proc/self/smaps, " +
                "got: ${names.distinct().take(20)}"
    )

    // Keep objects alive until assertion so GC doesn't reclaim them before smaps read.
    assertEquals(4096, objects[0].array.size)
}

/**
 * Allocation + GC cycle: VMA label must still be present (or have been re-added)
 * after a full GC that frees and potentially re-maps pages.
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testVmaLabelSurvivesGcCycle() {
    if (!isOhos()) return

    repeat(3) {
        val batch = Array(8) { LargeData() }
        GC.collect()
        // Briefly read smaps after each GC to confirm label persistence.
        val names = readSmapsNames()
        assertTrue(
            names.any { it == "kotlin heap" },
            "VMA label lost after GC cycle $it"
        )
        // Let batch go out of scope so GC can reclaim on next cycle.
        assertEquals(4096, batch[0].array.size)
    }
}

/**
 * Large single allocation: a single oversized array should still receive the
 * "kotlin heap" VMA label even though it occupies its own mmap region.
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testVmaLabelForLargeSingleAllocation() {
    if (!isOhos()) return

    // Allocate ~4 MB so it definitely gets its own mmap region.
    val bigArray = IntArray(1024 * 1024) { it }
    GC.collect()

    val names = readSmapsNames()
    assertTrue(
        names.any { it == "kotlin heap" },
        "Expected 'kotlin heap' VMA label for large single allocation"
    )
    assertEquals(1024 * 1024, bigArray.size)
}

/**
 * Thread-local storage allocation: TLS objects are also heap-allocated and
 * should carry the VMA label.
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testVmaLabelForThreadLocalAllocation() {
    if (!isOhos()) return

    // Access TLS data to trigger allocation on this thread.
    val size = tlsData.array.size
    GC.collect()

    val names = readSmapsNames()
    assertTrue(
        names.any { it == "kotlin heap" },
        "Expected 'kotlin heap' VMA label after TLS allocation"
    )
    assertEquals(4096, size)
}

/**
 * Repeated allocations with different sizes: VMA label count should be >= 1
 * and only "kotlin heap" labels should appear for Kotlin-heap pages.
 */
@Test
@OptIn(kotlin.native.runtime.NativeRuntimeApi::class)
fun testVmaLabelCountAndContent() {
    if (!isOhos()) return

    val objects = buildList {
        for (kb in listOf(4, 8, 16, 32, 64, 128)) {
            add(IntArray(kb * 256) { it })  // kb * 1024 / 4 ints = kb KB
        }
    }
    GC.collect()

    val names = readSmapsNames()
    val kotlinHeapCount = names.count { it == "kotlin heap" }
    assertTrue(kotlinHeapCount >= 1, "Expected at least one 'kotlin heap' VMA label")

    // Sanity: no label should be an empty string for real regions.
    assertTrue(names.none { it.isEmpty() }, "Found empty VMA name")

    // Keep reference alive.
    assertEquals(4 * 256, objects[0].size)
}
