// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, logical-disjunction-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: or operator evaluates right operand when left is false
 */

// TESTCASE NUMBER: 1

var called = false

fun sideEffect(): Boolean {
    called = true
    return true
}

fun box(): String {
    called = false
    val r = false || sideEffect()
    return if (r && called) "OK" else "NOK"
}
