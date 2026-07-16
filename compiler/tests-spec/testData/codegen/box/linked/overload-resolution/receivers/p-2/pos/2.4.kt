// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 2 -> sentence 2
 * NUMBER: 4
 * DESCRIPTION: current class companion object receiver beats superclass companion object receiver
 */

class Case1102 : Case1102Base() {

    companion object foo {
        var isCaseCompanionCalled = false
        fun foo() {
            isCaseCompanionCalled = true
        }
    }

    fun test(): String {
        foo.foo()
        return if (!isCaseBaseReceiverCalled && isCaseCompanionCalled) "OK" else "NOK"
    }
}

open class Case1102Base {
    companion object foo {
        var isCaseBaseReceiverCalled = false
        fun foo() {
            this.isCaseBaseReceiverCalled = true
        }
    }
}

// TESTCASE NUMBER: 1
fun box(): String = Case1102().test()
