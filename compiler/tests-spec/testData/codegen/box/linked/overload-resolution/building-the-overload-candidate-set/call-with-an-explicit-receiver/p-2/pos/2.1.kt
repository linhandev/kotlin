/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-an-explicit-receiver -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: call with explicit type receiver T.f() resolves static member on classifier type
 */

class Factory11202T {
    companion object {
        fun create11202T(): String = "OK"
    }
}

// TESTCASE NUMBER: 1
fun box(): String = if (Factory11202T.create11202T() == "OK") "OK" else "NOK"
