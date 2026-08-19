// WITH_STDLIB
// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration, inheritance-delegation -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: multiple interface implementation by class delegation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface I1 {
    fun foo(): String
}

interface I2 {
    fun bar(): String
}

class Impl1 : I1 {
    override fun foo() = "foo"
}

class Impl2 : I2 {
    override fun bar() = "bar"
}

class Delegate(i1: I1, i2: I2) : I1 by i1, I2 by i2

fun case_1() {
    val d = Delegate(Impl1(), Impl2())
    checkSubtype<String>(d.foo() + d.bar())
}
