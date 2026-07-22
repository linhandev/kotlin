// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: true condition evaluates true branch
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val flag = true
    return if (flag) "OK" else "NOK"
}
