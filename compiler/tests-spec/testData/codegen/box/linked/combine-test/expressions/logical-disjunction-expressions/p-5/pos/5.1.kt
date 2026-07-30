// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-disjunction-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: parentheses change ||/&& evaluation and side effects
 */

// TESTCASE NUMBER: 1
var n = 0
fun side(): Boolean { n++; return false }
fun withParen(): Boolean = (true || false) && side()
fun noParen(): Boolean = true || false && side()

fun box(): String {
    n = 0
    if (withParen()) return "NOK"
    if (n != 1) return "NOK"
    n = 0
    if (!noParen()) return "NOK"
    if (n != 0) return "NOK"
    return "OK"
}
