// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: join(second = "b", first = "a") binds named parameters out of order
 */

// TESTCASE NUMBER: 1

fun join(first: String, second: String): String = first + second

fun box(): String {
    if (join(second = "b", first = "a") != "ab") return "NOK"
    return "OK"
}
