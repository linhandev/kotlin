// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 *                type-system, introduction-1 -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: && skips right when null check fails
 */

// TESTCASE NUMBER: 1
var n = 0
fun len(s: String?): Int { n++; return s!!.length }
fun test(s: String?): Boolean = (s != null) && len(s) > 0
fun check(s: String?): Int { n = 0; test(s); return n }

fun box(): String {
    if (check(null) != 0) return "NOK"
    if (check("a") != 1) return "NOK"
    return "OK"
}
