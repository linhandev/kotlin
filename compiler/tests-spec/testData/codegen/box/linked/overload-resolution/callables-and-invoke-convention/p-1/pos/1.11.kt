// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 11
 * DESCRIPTION: call X(1, 2, 3) forwards vararg arguments to X.invoke
 */

var receivedArgs1143 = ""

class CallableHolder1143 {
    operator fun invoke(vararg values: Int) {
        receivedArgs1143 = values.joinToString(",")
    }
}

val target1143 = CallableHolder1143()

// TESTCASE NUMBER: 1
fun box(): String {
    target1143(1, 2, 3)
    return if (receivedArgs1143 == "1,2,3") "OK" else "NOK"
}
