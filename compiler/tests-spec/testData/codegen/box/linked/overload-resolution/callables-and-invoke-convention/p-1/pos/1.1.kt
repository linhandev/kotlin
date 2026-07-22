/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, callables-and-invoke-convention -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: function-like callable resolves as direct function call
 */

var funCalled1123 = false

fun target1123() {
    funCalled1123 = true
}

// TESTCASE NUMBER: 1
fun box(): String {
    target1123()
    return if (funCalled1123) "OK" else "NOK"
}
