// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: when { a == b -> "NOK"; else -> "OK" } picks else when a != b
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val a = 1
    val b = 2
    return when {
        a == b -> "NOK"
        else -> "OK"
    }
}
