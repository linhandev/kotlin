// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: ! evaluates its operand
 */

// TESTCASE NUMBER: 1
var n = 0
fun side(): Boolean { n++; return true }
fun test(): Boolean = !side()
fun check(): Int = n

fun box(): String {
    n = 0
    if (test()) return "NOK"
    if (check() != 1) return "NOK"
    return "OK"
}
