// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 77 -> sentence 77
 * NUMBER: 2
 * DESCRIPTION: semis newline NL separates two statements; newline-separated val bindings sum correctly
 */
package syntax.grammar.p77.pos2

// TESTCASE NUMBER: 1
fun box(): String {
    val a = 1
    val b = 2;
    return if (a + b == 3) "OK" else "NOK"
}
