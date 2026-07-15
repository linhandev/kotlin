// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 130 -> sentence 130
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 131 -> sentence 131
 * syntax-and-grammar, syntax-grammar -> paragraph 69 -> sentence 69
 * NUMBER: 2
 * DESCRIPTION: whenEntry multiple whenConditions comma separated
 */
package syntax.grammar.p130.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "when-conditions-130-2"
    val result = when (2) { 1, 2 -> expected; else -> "NOK" }
    if (result != expected) return "NOK"
    return "OK"
}
