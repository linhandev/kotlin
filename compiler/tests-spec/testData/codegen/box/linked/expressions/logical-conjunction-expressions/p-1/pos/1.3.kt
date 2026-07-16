// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, logical-conjunction-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: and operator evaluates right operand when left is true
 */

// TESTCASE NUMBER: 1

var called = false

fun sideEffect(): Boolean {
    called = true
    return true
}

fun box(): String {
    called = false
    val r = true && sideEffect()
    return if (r && called) "OK" else "NOK"
}
