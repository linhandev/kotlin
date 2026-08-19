// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: class delegation with data class interface implementation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): String
}

data class Impl(val name: String) : I {
    override fun foo() = name
}

class Delegate(i: I) : I by i

fun case_1() {
    checkSubtype<String>(Delegate(Impl("data")).foo())
}
