// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 129 -> sentence 129
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 128 -> sentence 128
 * syntax-and-grammar, syntax-grammar -> paragraph 130 -> sentence 130
 * NUMBER: 2
 * DESCRIPTION: whenExpression no subject when
 */
package syntax.grammar.p129.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "when-no-subject-129-2"
    val result = when { true -> expected; else -> "NOK" }
    if (result != expected) return "NOK"
    return "OK"
}
