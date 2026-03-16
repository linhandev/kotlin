// KIND: STANDALONE
// DISABLE_NATIVE: gcType=NOOP
// FREE_COMPILER_ARGS: -opt-in=kotlin.native.runtime.NativeRuntimeApi,kotlin.experimental.ExperimentalNativeApi -Xbinary=gc=cmc -Xallocator=crt

// Tests Regex matching, string builder, and complex stdlib operations under GC.
// Risk: Regex creates internal NFA/DFA state machines with many small objects;
// StringBuilder has mutable backing array that may need resize (new array + copy);
// these stdlib internals may have derived pointers or uncovered write barrier paths.

@file:OptIn(kotlin.experimental.ExperimentalNativeApi::class, kotlin.native.runtime.NativeRuntimeApi::class)

import kotlin.test.*
import kotlin.native.runtime.GC

@Test fun testRegexChannelGC() {
    // === Test 1: Regex match under GC ===
    val pattern = Regex("""(\d+)-(\w+)-([a-z]+)""")
    GC.collect()

    for (i in 0 until 200) {
        val input = "$i-test-abc"
        val match = pattern.matchEntire(input)
        assertNotNull(match, "T1: No match for '$input'")
        assertEquals(i.toString(), match.groupValues[1], "T1: Group 1 wrong for $i")
        assertEquals("test", match.groupValues[2], "T1: Group 2 wrong")
        if (i % 30 == 0) GC.collect()
    }

    // === Test 2: Complex regex with many groups + GC ===
    val complex = Regex("""^([A-Z])(\d{1,3})\.(\d{1,3})\.(\d{1,3})$""")
    GC.collect()

    val testCases = arrayOf("A1.2.3", "B10.20.30", "Z100.200.255")
    for (tc in testCases) {
        val m = complex.matchEntire(tc)
        assertNotNull(m, "T2: No match for '$tc'")
        GC.collect()
        // Groups must survive GC
        assertTrue(m.groupValues[1].length == 1, "T2: Group 1 wrong for '$tc'")
    }

    // === Test 3: Regex.findAll (lazy sequence) + GC ===
    val wordPattern = Regex("""\b\w+\b""")
    val longText = (0 until 100).joinToString(" ") { "word$it" }
    GC.collect()

    val words = wordPattern.findAll(longText).toList()
    GC.collect()
    assertEquals(100, words.size, "T3: findAll word count wrong")
    assertEquals("word0", words[0].value, "T3: First word wrong")
    assertEquals("word99", words[99].value, "T3: Last word wrong")

    // === Test 4: StringBuilder resize under GC ===
    val sb = StringBuilder()
    for (i in 0 until 1000) {
        sb.append("chunk-$i-")
        if (i % 100 == 0) GC.collect()
    }
    val result = sb.toString()
    assertTrue(result.startsWith("chunk-0-"), "T4: StringBuilder start wrong")
    assertTrue(result.contains("chunk-999-"), "T4: StringBuilder missing last chunk")

    // === Test 5: Multiple StringBuilders with GC between ===
    val builders = Array(50) { StringBuilder() }
    for (round in 0 until 20) {
        for (i in builders.indices) {
            builders[i].append("r$round-")
        }
        if (round % 5 == 0) GC.collect()
    }
    GC.collect()
    for (i in builders.indices) {
        val s = builders[i].toString()
        assertTrue(s.startsWith("r0-"), "T5: Builder $i start wrong")
        assertTrue(s.endsWith("r19-"), "T5: Builder $i end wrong")
    }

    // === Test 6: String.replace with regex + GC ===
    val template = "Hello NAME, your ID is NUM and score is NUM."
    GC.collect()

    for (i in 0 until 100) {
        var result2 = template.replace("NAME", "user$i")
        result2 = result2.replace("NUM", i.toString())
        GC.collect()
        assertTrue(result2.contains("user$i"), "T6: Replace failed for $i")
    }

    // === Test 7: Regex.split + GC ===
    val csvPattern = Regex(",")
    for (round in 0 until 50) {
        val csv = (0 until 100).joinToString(",") { "field$it" }
        val fields = csvPattern.split(csv)
        if (round % 10 == 0) GC.collect()
        assertEquals(100, fields.size, "T7: Split count wrong at round $round")
        assertEquals("field0", fields[0], "T7: First field wrong")
        assertEquals("field99", fields[99], "T7: Last field wrong")
    }

    // === Test 8: buildString + GC ===
    val built = buildString {
        for (i in 0 until 500) {
            append("[$i]")
            if (i % 100 == 0) GC.collect()
        }
    }
    assertTrue(built.startsWith("[0]"), "T8: buildString start wrong")
    assertTrue(built.contains("[499]"), "T8: buildString missing last")

    println("PASS")
}
