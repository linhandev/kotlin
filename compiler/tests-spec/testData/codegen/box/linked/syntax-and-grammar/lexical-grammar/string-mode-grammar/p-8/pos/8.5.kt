// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 8 -> sentence 8
 * NUMBER: 5
 * DESCRIPTION: LineStrExprStart ${if (true) 1 else 0} conditional expression
 */
// TESTCASE NUMBER: 1
fun pickBranch(flag: Boolean): Int = if (flag) 1 else 0

fun box(): String {
    if ("${pickBranch(true)}" != "1") return "NOK"
    if ("${pickBranch(false)}" != "0") return "NOK"
    return "OK"
}
