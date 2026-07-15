// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 126 -> sentence 126
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 68 -> sentence 68
 * syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * NUMBER: 2
 * DESCRIPTION: superExpression labeled super
 */
// TESTCASE NUMBER: 1
package syntax.grammar.p126.pos2

open class Base {
    open fun label(): String = "codegen-126-2"
}

class Derived : Base() {
    override fun label(): String = super@Derived.label()
}

fun box(): String = if (Derived().label() == "codegen-126-2") "OK" else "NOK"
