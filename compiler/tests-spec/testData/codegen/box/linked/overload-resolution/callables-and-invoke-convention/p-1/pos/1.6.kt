/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: qualified this@A(Y) expands to this@A.invoke(Y) as property-like callable
 */

class Outer1136 {
    var invoked = false

    operator fun invoke() {
        invoked = true
    }

    inner class Inner {
        fun call(): String {
            this@Outer1136()
            return if (this@Outer1136.invoked) "OK" else "NOK"
        }
    }
}

// TESTCASE NUMBER: 1
fun box(): String {
    return Outer1136().Inner().call()
}
