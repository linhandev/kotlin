// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: implicit this receiver has higher priority than companion object receiver
 */

class Case1102 : Case1102Base() {

    companion object foo {
        var isCompanionObjectReceiverCalled = false
        operator fun invoke() {}
        fun foo() {
            this.isCompanionObjectReceiverCalled = true
        }
    }

    fun case() {
        foo()
    }
}

open class Case1102Base {
    var isImplicitReceiverCalled = false

    fun foo() {
        this.isImplicitReceiverCalled = true
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    val test = Case1102()
    test.case()
    return if (test.isImplicitReceiverCalled && !Case1102.isCompanionObjectReceiverCalled) "OK" else "NOK"
}
