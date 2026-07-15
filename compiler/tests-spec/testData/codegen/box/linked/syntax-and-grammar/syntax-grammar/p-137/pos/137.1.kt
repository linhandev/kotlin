// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 137 -> sentence 137
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 78 -> sentence 78
 * NUMBER: 1
 * DESCRIPTION: jumpExpression return expression
 */
package syntax.grammar.p137.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "return-137"
    fun inner(): String {
        return expected
    }
    if (inner() != expected) return "NOK"
    return "OK"
}
