// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: postfix ++ operand short-circuits second side
 */

// TESTCASE NUMBER: 1
var n = 0
fun test(): Boolean = (n++ > 0) && (n++ > 0)
fun check(): Int = n

fun box(): String {
    n = 0
    if (test()) return "NOK"
    if (check() != 1) return "NOK"
    return "OK"
}
