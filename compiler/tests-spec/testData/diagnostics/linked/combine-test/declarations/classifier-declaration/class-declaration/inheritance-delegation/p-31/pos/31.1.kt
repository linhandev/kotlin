// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: class delegation with sealed class interface implementation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I {
    fun foo(): String
}

sealed class Base : I {
    override fun foo() = "base"
}

class Derived : Base()

class Delegate(i: I) : I by i

fun case_1() {
    checkSubtype<String>(Delegate(Derived()).foo())
}
