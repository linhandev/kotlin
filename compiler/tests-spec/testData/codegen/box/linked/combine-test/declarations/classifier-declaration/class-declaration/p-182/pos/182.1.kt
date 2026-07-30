// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 182 -> sentence 182
 * PRIMARY LINKS: inheritance, overriding -> paragraph 182 -> sentence 182
 *                declarations, property-declaration -> paragraph 182 -> sentence 182
 *                inheritance, inheriting -> paragraph 182 -> sentence 182
 * NUMBER: 1
 * DESCRIPTION: open var can be overridden by override var and the subclass setter replaces inherited mutability in a class declaration
 */

// TESTCASE NUMBER: 1
open class Counter {
    open var count: Int = 0
}

class AdjustableCounter : Counter() {
    override var count: Int = 1
}

// TESTCASE NUMBER: 2
open class Ledger {
    open var balance: Int = 10
    fun doubled(): Int = balance * 2
}

class MutableLedger : Ledger() {
    override var balance: Int = 20
}

// TESTCASE NUMBER: 3
open class TagHolder {
    open var tag: String = "base"
}

class Tagged : TagHolder() {
    override var tag: String = "child"
}

fun box(): String {
    val base = Counter()
    val adjustable = AdjustableCounter()
    if (base.count != 0) return "NOK: base-count"
    if (adjustable.count != 1) return "NOK: adjustable-init"
    adjustable.count = 5
    if (adjustable.count != 5) return "NOK: adjustable-set"
    if (base.count == adjustable.count) return "NOK: count-not-independent"

    val asCounter: Counter = adjustable
    asCounter.count = 7
    if (asCounter.count != 7) return "NOK: counter-ref-set"

    val ledger = MutableLedger()
    if (ledger.balance != 20) return "NOK: ledger-init"
    ledger.balance = 4
    if (ledger.doubled() != 8) return "NOK: ledger-doubled"

    val tagged: TagHolder = Tagged()
    tagged.tag = "updated"
    if (tagged.tag != "updated") return "NOK: tag-set"
    return "OK"
}
