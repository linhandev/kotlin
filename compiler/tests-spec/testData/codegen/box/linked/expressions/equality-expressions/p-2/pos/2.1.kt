// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, equality-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: a === b for same reference; line break before !== still compares references
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val a = "OK"
    val b = a
    return if (a === b && a
        !== "NOK") "OK" else "NOK"
}
