// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 129 -> sentence 129
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 128 -> sentence 128
 * syntax-and-grammar, syntax-grammar -> paragraph 130 -> sentence 130
 * NUMBER: 1
 * DESCRIPTION: whenExpression subject when
 */
package syntax.grammar.p129.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "when-subject-129-1"
    val result = when (1) { 1 -> expected; else -> "NOK" }
    if (result != expected) return "NOK"
    return "OK"
}
