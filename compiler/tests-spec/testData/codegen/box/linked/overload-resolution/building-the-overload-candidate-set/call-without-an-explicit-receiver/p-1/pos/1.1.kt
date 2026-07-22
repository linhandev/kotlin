/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-without-an-explicit-receiver -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: simple-path call foo() resolves top-level callable without explicit receiver
 */

fun helper11205(): String = "OK"

// TESTCASE NUMBER: 1
fun box(): String {
    val result = helper11205()
    return if (result == "OK") "OK" else "NOK: $result"
}
