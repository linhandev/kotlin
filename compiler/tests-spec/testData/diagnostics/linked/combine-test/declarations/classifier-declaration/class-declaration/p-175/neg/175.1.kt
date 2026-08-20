// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 175 -> sentence 175
 * PRIMARY LINKS: inheritance, overriding -> paragraph 175 -> sentence 175
 *                inheritance, inheriting -> paragraph 175 -> sentence 175
 * NUMBER: 1
 * DESCRIPTION: override modifier in a class declaration is rejected when no matching open supertype member exists to override
 */

// TESTCASE NUMBER: 1
open class Base

class Child : Base() {
    <!NOTHING_TO_OVERRIDE!>override<!> fun ghost(): Int = 0
}

// TESTCASE NUMBER: 2
open class WithMember {
    open fun present(): Int = 1
}

class MismatchedSignature : WithMember() {
    <!NOTHING_TO_OVERRIDE!>override<!> fun present(flag: Boolean): Int = 2
}

// TESTCASE NUMBER: 3
open class Holder {
    open val value: Int = 1
}

class Phantom : Holder() {
    <!NOTHING_TO_OVERRIDE!>override<!> val missing: Int = 2
}
