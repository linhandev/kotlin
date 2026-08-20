// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 199 -> sentence 199
 * PRIMARY LINKS: inheritance, overriding -> paragraph 199 -> sentence 199
 *                declarations, declaration-visibility -> paragraph 199 -> sentence 199
 *                inheritance, inheriting -> paragraph 199 -> sentence 199
 * NUMBER: 1
 * DESCRIPTION: internal override cannot replace a public open superclass member (CANNOT_WEAKEN_ACCESS_PRIVILEGE) in a class declaration
 */

// TESTCASE NUMBER: 1
open class Base {
    public open fun f(): Int = 1
}

class Child : Base() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE!>internal<!> override fun f(): Int = 2
}

// TESTCASE NUMBER: 2
open class Holder {
    public open val label: String = "base"
}

class HiddenHolder : Holder() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE!>internal<!> override val label: String = "child"
}

// TESTCASE NUMBER: 3
open class Slot {
    public open var value: Int = 1
}

class NarrowSlot : Slot() {
    <!CANNOT_WEAKEN_ACCESS_PRIVILEGE!>internal<!> override var value: Int = 2
}
