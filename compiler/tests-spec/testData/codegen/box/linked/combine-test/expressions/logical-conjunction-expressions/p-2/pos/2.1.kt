// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: true && evaluates right operand
 */

// TESTCASE NUMBER: 1
var n = 0
fun side(): Boolean { n++; return true }
fun test(): Boolean = true && side()
fun check(): Int = n

fun box(): String {
    n = 0
    if (!test()) return "NOK"
    if (check() != 1) return "NOK"
    return "OK"
}
