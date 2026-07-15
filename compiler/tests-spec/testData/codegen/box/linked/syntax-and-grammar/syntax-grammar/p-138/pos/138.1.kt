// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 138 -> sentence 138
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 63 -> sentence 63
 * syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * NUMBER: 1
 * DESCRIPTION: callableReference function reference
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p138.pos1

fun inc(n: Int): Int = n + 1

fun box(): String {
    val f: (Int) -> Int = ::inc
    return if (f(1) == 2) "OK" else "NOK"
}
