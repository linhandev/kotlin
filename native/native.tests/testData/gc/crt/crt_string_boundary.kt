// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests string handling edge cases under CRT GC.
// Risk: String objects have special layout; substring/concat may create
// derived pointers; large strings may cross allocation region boundaries.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.concurrent.AtomicInt
import kotlin.native.runtime.GC
import kotlin.native.concurrent.Worker

@Test fun testStringBoundary() {
    // === Test 1: Large string survival across GC ===
    val bigString = "x".repeat(1_000_000)
    GC.collect()
    assertEquals(1_000_000, bigString.length, "T1: Large string length changed after GC")
    assertTrue(bigString.all { it == 'x' }, "T1: Large string content corrupted")

    // === Test 2: Many substrings referencing same base string ===
    val base = "abcdefghijklmnopqrstuvwxyz".repeat(1000) // 26000 chars
    val substrings = Array(100) { i ->
        base.substring(i * 260, i * 260 + 260)
    }
    GC.collect()
    for (i in substrings.indices) {
        assertEquals(260, substrings[i].length, "T2: Substring $i length wrong after GC")
        assertEquals(base.substring(i * 260, i * 260 + 260), substrings[i],
            "T2: Substring $i content wrong after GC")
    }

    // === Test 3: String concatenation storm + GC ===
    var accumulated = ""
    for (round in 0 until 500) {
        accumulated += "x"
        if (round % 100 == 0) GC.collect()
    }
    assertEquals(500, accumulated.length, "T3: Concatenated string length wrong")

    // === Test 4: Unicode boundary strings + GC ===
    val unicodeStrings = arrayOf(
        "\u0000",                          // null char
        "\u0001",                          // control char
        "\u007F",                          // DEL
        "\u0080",                          // first 2-byte UTF-8
        "\u07FF",                          // last 2-byte UTF-8
        "\u0800",                          // first 3-byte UTF-8
        "\uFFFF",                          // last 3-byte UTF-8 (BMP)
        "\uD800\uDC00",                    // surrogate pair encoding U+10000 (valid supplementary character)
        "Hello\u0000World",                // embedded null
        "\u4E2D\u6587\u6D4B\u8BD5",        // Chinese text
        "\uD83D\uDE00",                    // emoji (U+1F600)
        "a\u0300",                         // combining accent
    )

    GC.collect()
    for (i in unicodeStrings.indices) {
        val s = unicodeStrings[i]
        assertTrue(s.length > 0, "T4: Unicode string $i became empty after GC")
    }

    // Store lengths before GC
    val lengths = unicodeStrings.map { it.length }
    GC.collect()
    GC.collect()
    for (i in unicodeStrings.indices) {
        assertEquals(lengths[i], unicodeStrings[i].length, "T4: Unicode string $i length changed after GC")
    }

    // === Test 5: Concurrent string operations + GC ===
    val concatErrors = AtomicInt(0)
    val workers = Array(4) { Worker.start() }
    val futures = workers.mapIndexed { idx, worker ->
        worker.execute(kotlin.native.concurrent.TransferMode.SAFE, { Pair(idx, concatErrors) }) { (workerIdx, errors) ->
            for (round in 0 until 100) {
                val s1 = "worker-$workerIdx-round-$round"
                val s2 = s1 + "-suffix"
                val s3 = s2.substring(0, s1.length)
                if (s3 != s1) errors.incrementAndGet()
                if (round % 20 == 0) GC.collect()
            }
            0
        }
    }
    for (f in futures) f.result
    for (w in workers) w.requestTermination().result
    assertEquals(0, concatErrors.value, "T5: Concurrent string operation errors")

    // === Test 6: String hashCode stability across GC ===
    val testStrings = Array(100) { "hash-test-string-$it" }
    val hashes = testStrings.map { it.hashCode() }
    repeat(5) { GC.collect() }
    for (i in testStrings.indices) {
        assertEquals(hashes[i], testStrings[i].hashCode(), "T6: String hashCode changed after GC for index $i")
    }

    println("PASS")
}
