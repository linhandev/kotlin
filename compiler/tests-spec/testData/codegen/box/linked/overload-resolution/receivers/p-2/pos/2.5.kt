// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 2 -> sentence 2
 * NUMBER: 5
 * DESCRIPTION: superclass companion object receivers are prioritized by inheritance order
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val case0 = Case1102Base0().case0()
    val case1 = Case1102Base1().case1()
    val case2 = Case1102Base2().case2()
    return if (case0 && case1 && case2) "OK" else "NOK"
}

class Case1102Base2 : Case1102Base1() {

    companion object foo {
        var isCaseCompanionCalled = false
        fun foo() {
            isCaseCompanionCalled = true
        }
    }

    fun case2(): Boolean {
        foo.foo()
        val res = !isCaseBase0ReceiverCalled && !isCaseBaseReceiverCalled && isCaseCompanionCalled
        isCaseCompanionCalled = false
        return res
    }
}

open class Case1102Base1 : Case1102Base0() {
    companion object foo {
        var isCaseBaseReceiverCalled = false
        fun foo() {
            this.isCaseBaseReceiverCalled = true
        }
    }

    fun case1(): Boolean {
        foo.foo()
        val res = !isCaseBase0ReceiverCalled && Case1102Base1.isCaseBaseReceiverCalled && !Case1102Base2.isCaseCompanionCalled
        isCaseBaseReceiverCalled = false
        return res
    }
}

open class Case1102Base0 {
    companion object foo {
        var isCaseBase0ReceiverCalled = false
        fun foo() {
            this.isCaseBase0ReceiverCalled = true
        }
    }

    fun case0(): Boolean {
        foo.foo()
        val res = isCaseBase0ReceiverCalled && !Case1102Base1.isCaseBaseReceiverCalled && !Case1102Base2.isCaseCompanionCalled
        isCaseBase0ReceiverCalled = false
        return res
    }
}
