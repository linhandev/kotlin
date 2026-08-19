// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-disjunction-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: Boolean and/or/xor evaluate both operands
 */

// TESTCASE NUMBER: 1
var n = 0
fun side(): Boolean { n++; return true }

fun box(): String {
    n = 0
    if (!false.or(side())) return "NOK"
    if (n != 1) return "NOK"
    n = 0
    if (false.and(side())) return "NOK"
    if (n != 1) return "NOK"
    n = 0
    if (!false.xor(side())) return "NOK"
    if (n != 1) return "NOK"
    n = 0
    if (!(true || side())) return "NOK"
    if (n != 0) return "NOK"
    return "OK"
}
