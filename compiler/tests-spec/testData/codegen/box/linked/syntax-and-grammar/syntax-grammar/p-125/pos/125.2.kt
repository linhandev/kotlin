// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 125 -> sentence 125
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 68 -> sentence 68
 * NUMBER: 2
 * DESCRIPTION: thisExpression labeled this
 */
package syntax.grammar.p125.pos2

class Wrapper {
    fun value(): String = this@Wrapper.let { "OK" }
}

// TESTCASE NUMBER: 1
fun box(): String = Wrapper().value()
