/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, the-forms-of-call-expression -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: infix function call a foo b resolves infix operator function
 */

infix fun String.append1121(other: String): String = this + other

// TESTCASE NUMBER: 1
fun box(): String {
    val result = "OK" append1121 "!"
    return if (result == "OK!") "OK" else "NOK: $result"
}
