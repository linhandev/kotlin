/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, type-inference-and-overload-resolution -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: overload resolution uses known argument types before type inference on lambdas
 */

fun route117(x: Int): String = "int"
fun route117(x: String): String = "str"

// TESTCASE NUMBER: 1
fun box(): String {
    if (route117(1) != "int") return "NOK"
    if (route117("x") != "str") return "NOK"
    return "OK"
}
