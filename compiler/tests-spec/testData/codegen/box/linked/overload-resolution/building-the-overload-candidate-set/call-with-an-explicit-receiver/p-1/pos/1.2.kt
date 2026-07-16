/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-an-explicit-receiver -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: extension callable f resolves on explicit receiver e as e.f()
 */

class Host11202E(val tag: String)

fun Host11202E.wrap11202(): String = "[$tag]"

// TESTCASE NUMBER: 1
fun box(): String {
    val result = Host11202E("OK").wrap11202()
    return if (result == "[OK]") "OK" else "NOK: $result"
}
