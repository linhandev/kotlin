// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 77 -> sentence 77
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 76 -> sentence 76
 * NUMBER: 4
 * DESCRIPTION: semis mixed semicolon and newline separators
 */
package syntax.grammar.p77.pos4

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 1;
    val b = 2; val c = 3
    return if (a + b + c == 6) "OK" else "NOK"
}
