/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, the-forms-of-call-expression -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: call with explicit receiver a.foo() resolves member function
 */

class Holder1121 {
    fun read(): String = "OK"
}

// TESTCASE NUMBER: 1
fun box(): String {
    val result = Holder1121().read()
    return if (result == "OK") "OK" else "NOK: $result"
}
