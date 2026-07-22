/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 12
 * DESCRIPTION: call X { } forwards trailing lambda to X.invoke
 */

var receivedToken1144 = ""

class CallableHolder1144 {
    operator fun invoke(block: () -> String) {
        receivedToken1144 = block()
    }
}

val target1144 = CallableHolder1144()

// TESTCASE NUMBER: 1
fun box(): String {
    target1144 { "token" }
    return if (receivedToken1144 == "token") "OK" else "NOK"
}
