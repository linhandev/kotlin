// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, logical-conjunction-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: and operator combines two Boolean operands with short-circuit &&
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (!(true && true)) return "NOK"
    if (true && false) return "NOK"
    if (false && true) return "NOK"
    if (false && false) return "NOK"
    return "OK"
}
