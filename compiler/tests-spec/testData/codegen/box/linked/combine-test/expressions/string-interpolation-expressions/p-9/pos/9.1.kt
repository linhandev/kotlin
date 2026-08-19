// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: extension property access can be used inside ${} interpolation
 */

// TESTCASE NUMBER: 1
fun test(s: String): String = "len=${s.length}"

fun box(): String {
    if (test("abc") != "len=3") return "NOK"
    if (test("") != "len=0") return "NOK"
    return "OK"
}
