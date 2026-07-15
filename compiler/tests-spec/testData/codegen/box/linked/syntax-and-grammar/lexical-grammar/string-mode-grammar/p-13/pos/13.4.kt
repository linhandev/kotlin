// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 13 -> sentence 13
 * NUMBER: 4
 * DESCRIPTION: MultiLineStrExprStart conditional expression in multiline
 */
// TESTCASE NUMBER: 1
fun pickBranch(flag: Boolean): Int = if (flag) 1 else 0

fun box(): String {
    val multiline = """${pickBranch(true)}
line2"""
    if (multiline != "1\nline2") return "NOK"
    return "OK"
}
