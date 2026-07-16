/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 9
 * DESCRIPTION: call X(name = value) forwards named parameters to X.invoke(name = value)
 */

var receivedName1140 = ""

class CallableHolder1140 {
    operator fun invoke(name: String) {
        receivedName1140 = name
    }
}

val target1140 = CallableHolder1140()

// TESTCASE NUMBER: 1
fun box(): String {
    target1140(name = "x")
    return if (receivedName1140 == "x") "OK" else "NOK"
}
