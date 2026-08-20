// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 181 -> sentence 181
 * PRIMARY LINKS: inheritance, overriding -> paragraph 181 -> sentence 181
 *                declarations, property-declaration -> paragraph 181 -> sentence 181
 *                inheritance, inheriting -> paragraph 181 -> sentence 181
 * NUMBER: 1
 * DESCRIPTION: override val cannot replace open var because val is a stronger mutability than var in a class declaration
 */

// TESTCASE NUMBER: 1
open class MutableSlot {
    open var slot: Int = 1
}

class FrozenSlot : MutableSlot() {
    override <!VAR_OVERRIDDEN_BY_VAL!>val<!> slot: Int = 2
}

// TESTCASE NUMBER: 2
open class Writable {
    open var label: String = "a"
}

class ReadOnly : Writable() {
    override <!VAR_OVERRIDDEN_BY_VAL!>val<!> label: String = "b"
}

// TESTCASE NUMBER: 3
open class Gauge {
    open var level: Int
        get() = 1
        set(_) {}
}

class FixedGauge : Gauge() {
    override <!VAR_OVERRIDDEN_BY_VAL!>val<!> level: Int = 3
}
