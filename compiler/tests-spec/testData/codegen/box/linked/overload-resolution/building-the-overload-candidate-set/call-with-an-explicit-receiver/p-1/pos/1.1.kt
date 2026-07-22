/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-an-explicit-receiver -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: member callable f resolves on explicit receiver e as e.f()
 */

class Host11202 {
    fun read11202(): String = "OK"
}

// TESTCASE NUMBER: 1
fun box(): String {
    val result = Host11202().read11202()
    return if (result == "OK") "OK" else "NOK: $result"
}
