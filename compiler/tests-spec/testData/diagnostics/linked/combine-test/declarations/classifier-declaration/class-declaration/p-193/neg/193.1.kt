// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 193 -> sentence 193
 * PRIMARY LINKS: inheritance, overriding -> paragraph 193 -> sentence 193
 *                inheritance, classifier-type-inheritance, open-classes -> paragraph 193 -> sentence 193
 *                declarations, classifier-declaration, class-declaration, abstract-classes -> paragraph 193 -> sentence 193
 *                inheritance, inheriting -> paragraph 193 -> sentence 193
 * NUMBER: 1
 * DESCRIPTION: a non-abstract class cannot use abstract override on an inherited open member (ABSTRACT_FUNCTION_IN_NON_ABSTRACT_CLASS / ABSTRACT_PROPERTY_IN_NON_ABSTRACT_CLASS)
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): Int = 1
}

class Bad : Base() {
    <!ABSTRACT_FUNCTION_IN_NON_ABSTRACT_CLASS!>abstract<!> override fun f(): Int
}

// TESTCASE NUMBER: 2
open class Meter {
    open val reading: Int = 1
}

class BadMeter : Meter() {
    <!ABSTRACT_PROPERTY_IN_NON_ABSTRACT_CLASS!>abstract<!> override val reading: Int
}

// TESTCASE NUMBER: 3
open class Named {
    open fun name(): String = "base"
}

open class StillConcrete : Named() {
    <!ABSTRACT_FUNCTION_IN_NON_ABSTRACT_CLASS!>abstract<!> override fun name(): String
}
