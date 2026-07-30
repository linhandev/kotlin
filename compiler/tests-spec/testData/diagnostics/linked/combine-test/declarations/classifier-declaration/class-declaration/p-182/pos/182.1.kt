// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 182 -> sentence 182
 * PRIMARY LINKS: inheritance, overriding -> paragraph 182 -> sentence 182
 *                declarations, property-declaration -> paragraph 182 -> sentence 182
 *                inheritance, inheriting -> paragraph 182 -> sentence 182
 * NUMBER: 1
 * DESCRIPTION: type inference for override var replacing open var in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Counter {
    open var count: Int = 0
}

class AdjustableCounter : Counter() {
    override var count: Int = 1
}

fun case1() {
    val adjustable = AdjustableCounter()
    adjustable checkType { check<AdjustableCounter>() }
    checkSubtype<Counter>(adjustable)
    adjustable.count checkType { check<Int>() }

    val asCounter: Counter = adjustable
    asCounter.count checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
open class Ledger {
    open var balance: Int = 10
    fun doubled(): Int = balance * 2
}

class MutableLedger : Ledger() {
    override var balance: Int = 20
}

fun case2() {
    val ledger: Ledger = MutableLedger()
    ledger.balance checkType { check<Int>() }
    ledger.doubled() checkType { check<Int>() }
}
