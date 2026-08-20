// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, property-declaration -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: declarations, property-declaration, getters-and-setters -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: cannot override var with val
 */

// TESTCASE NUMBER: 1
open class Base {
    open var x: Int = 1
}

class Derived : Base() {
    override <!VAR_OVERRIDDEN_BY_VAL!>val<!> x: Int = 2
}

fun case_1() = Derived()
