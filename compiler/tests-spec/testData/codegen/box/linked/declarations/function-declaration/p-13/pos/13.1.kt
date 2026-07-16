// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: IntArray spread into vararg Int at runtime
 */

// TESTCASE NUMBER: 1
fun join(vararg items: Int): String = items.joinToString("-")

fun box(): String {
    val arr = intArrayOf(4, 5, 6)
    return if (join(*arr) == "4-5-6") "OK" else "NOK"
}
