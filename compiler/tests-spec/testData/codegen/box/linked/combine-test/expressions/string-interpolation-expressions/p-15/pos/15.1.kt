// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 15 -> sentence 15
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 15 -> sentence 15
 *                expressions, elvis-operator-expressions -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: safe call and Elvis operator can be used inside ${} interpolation
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String = "len=${x?.length ?: -1}"

fun box(): String {
    if (test("abc") != "len=3") return "NOK"
    if (test(null) != "len=-1") return "NOK"
    return "OK"
}
