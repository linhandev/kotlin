// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 201 -> sentence 201
 * PRIMARY LINKS: inheritance, overriding -> paragraph 201 -> sentence 201
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 201 -> sentence 201
 *                inheritance, inheriting -> paragraph 201 -> sentence 201
 * NUMBER: 1
 * DESCRIPTION: override return type must be a subtype of the open superclass member return type (RETURN_TYPE_MISMATCH_ON_OVERRIDE / PROPERTY_TYPE_MISMATCH_ON_OVERRIDE)
 */

// TESTCASE NUMBER: 1
open class Base {
    open fun f(): Int = 1
}

class Child : Base() {
    override fun f(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = "x"
}

// TESTCASE NUMBER: 2
open class Source {
    open fun text(): String = "base"
}

class BadSource : Source() {
    override fun text(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 1
}

// TESTCASE NUMBER: 3
open class Holder {
    open val label: CharSequence = "base"
}

class BadHolder : Holder() {
    override val label: <!PROPERTY_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 1
}
