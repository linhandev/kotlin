/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: lambda with extension function type exposes this receiver inside lambda body
 */

// TESTCASE NUMBER: 1
fun box(): String {
    val block: String.() -> String = { this + "!" }
    val result = with("OK", block)
    return if (result == "OK!") "OK" else "NOK: $result"
}
