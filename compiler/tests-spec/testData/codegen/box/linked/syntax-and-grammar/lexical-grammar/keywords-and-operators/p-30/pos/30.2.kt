// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 30 -> sentence 30
 * NUMBER: 2
 * DESCRIPTION: DOUBLE_ARROW token in string literal containing =>
 */
// TESTCASE NUMBER: 1
fun box(): String {
    val arrow = "=>"
    check(arrow.length == 2 && arrow[0] == '=' && arrow[1] == '>')
    return "OK"
}
