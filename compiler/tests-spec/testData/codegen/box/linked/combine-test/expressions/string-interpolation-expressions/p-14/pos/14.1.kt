// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 14 -> sentence 14
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: nullable reference interpolated as literal null text
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String = "x=$x"

fun box(): String {
    if (test("hi") != "x=hi") return "NOK"
    if (test(null) != "x=null") return "NOK"
    return "OK"
}
