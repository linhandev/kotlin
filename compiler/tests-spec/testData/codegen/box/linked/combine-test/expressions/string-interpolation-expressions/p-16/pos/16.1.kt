// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 16 -> sentence 16
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: when expression can be used inside ${} interpolation
 */

// TESTCASE NUMBER: 1
fun test(x: Int): String = "r=${when (x) { 1 -> "one"; else -> "other" }}"

fun box(): String {
    if (test(1) != "r=one") return "NOK"
    if (test(2) != "r=other") return "NOK"
    if (test(0) != "r=other") return "NOK"
    return "OK"
}
