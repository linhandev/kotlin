// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: false && skips right operand
 */

// TESTCASE NUMBER: 1
var n = 0
fun side(): Boolean { n++; return true }
fun test(): Boolean = false && side()
fun check(): Int = n

fun box(): String {
    if (test()) return "NOK"
    if (check() != 0) return "NOK"
    return "OK"
}
