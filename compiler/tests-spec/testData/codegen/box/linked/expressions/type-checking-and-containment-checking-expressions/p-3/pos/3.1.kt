// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, type-checking-and-containment-checking-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Any value "OK" passes x is String and x !is Int checks
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val x: Any = "OK"
    if (x is String && x !is Int) return "OK"
    return "NOK"
}
