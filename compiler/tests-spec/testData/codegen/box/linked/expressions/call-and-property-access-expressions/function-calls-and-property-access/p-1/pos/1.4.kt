// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: sum() uses defaults; sum(y = 5, x = 10) overrides both parameters
 */

// TESTCASE NUMBER: 1

fun sum(x: Int = 1, y: Int = 2): Int = x + y

fun box(): String {
    if (sum() != 3) return "NOK"
    if (sum(y = 5, x = 10) != 15) return "NOK"
    return "OK"
}
