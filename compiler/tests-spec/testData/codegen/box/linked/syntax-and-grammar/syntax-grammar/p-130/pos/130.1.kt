// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 130 -> sentence 130
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 131 -> sentence 131
 * syntax-and-grammar, syntax-grammar -> paragraph 69 -> sentence 69
 * NUMBER: 1
 * DESCRIPTION: whenEntry when branch
 */
package syntax.grammar.p130.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val expected = "when-entry-130-1"
    val result = when (2) { 1 -> "NOK"; 2 -> expected; else -> "NOK" }
    if (result != expected) return "NOK"
    return "OK"
}
