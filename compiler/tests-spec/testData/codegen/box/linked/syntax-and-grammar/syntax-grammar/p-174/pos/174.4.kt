// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 174 -> sentence 174
 * NUMBER: 4
 * DESCRIPTION: simpleIdentifier soft keyword get in property getter
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p174.pos4

val answer173: Int
    get() = 42

fun box(): String {
    when { answer173 == 42 -> return "OK"; else -> return "NOK" }
}
