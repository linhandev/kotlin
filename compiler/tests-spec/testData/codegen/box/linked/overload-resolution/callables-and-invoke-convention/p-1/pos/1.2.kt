/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: property-like callable X() expands to X.invoke()
 */

var invokeCalled1123 = false

class CallableHolder1123 {
    operator fun invoke() {
        invokeCalled1123 = true
    }
}

val target1123 = CallableHolder1123()

// TESTCASE NUMBER: 1
fun box(): String {
    target1123()
    return if (invokeCalled1123) "OK" else "NOK"
}
