// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 126 -> sentence 126
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * syntax-and-grammar, syntax-grammar -> paragraph 173 -> sentence 173
 * NUMBER: 1
 * DESCRIPTION: superExpression open class override
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p126.pos1

open class B {
    open fun v(): Int = 1
}

class D : B() {
    override fun v(): Int = super.v() + 1
}

fun box(): String = if (D().v() == 2) "OK" else "NOK"
