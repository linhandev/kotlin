// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 77 -> sentence 77
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 76 -> sentence 76
 * NUMBER: 3
 * DESCRIPTION: semis trailing semicolon after statement in block; trailing semicolon does not change statement sum
 */
package syntax.grammar.p77.pos3

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 100; val b = 200;
    return if (a + b == 300) "OK" else "NOK"
}
