// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 77 -> sentence 77
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 76 -> sentence 76
 * NUMBER: 1
 * DESCRIPTION: semis semicolon separates two statements; semicolon-separated val bindings sum correctly
 */
package syntax.grammar.p77.pos1

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 1; val b = 2
    val sum = a + b
    return if (sum == 3) "OK" else "NOK"
}
