// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: Char simple identifier interpolation uses character value directly
 */

// TESTCASE NUMBER: 1
fun test(c: Char): String = "$c"

fun box(): String {
    if (test('A') != "A") return "NOK"
    if (test('z') != "z") return "NOK"
    if (test(' ') != " ") return "NOK"
    return "OK"
}
