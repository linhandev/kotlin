// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: branchless conditional expression if (condition) else; is valid
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val flag = true
    if (flag) else;
    if (false) else;
    return "OK"
}
