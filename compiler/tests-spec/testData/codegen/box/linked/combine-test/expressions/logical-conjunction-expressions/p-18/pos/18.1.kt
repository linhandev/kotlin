// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: takeIf lambda always runs unlike &&
 */

// TESTCASE NUMBER: 1
var n = 0
fun test(): Int? = 1.takeIf { n++; false }
fun check(): Int = n

fun box(): String {
    n = 0
    if (test() != null) return "NOK"
    if (check() != 1) return "NOK"
    return "OK"
}
