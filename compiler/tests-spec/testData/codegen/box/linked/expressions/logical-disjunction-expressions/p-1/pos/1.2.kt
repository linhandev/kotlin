// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, logical-disjunction-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: or operator combines two Boolean operands with short-circuit ||
 */

// TESTCASE NUMBER: 1

fun box(): String {
    if (!(false || true)) return "NOK"
    if (!(true || false)) return "NOK"
    if (false || false) return "NOK"
    return "OK"
}
