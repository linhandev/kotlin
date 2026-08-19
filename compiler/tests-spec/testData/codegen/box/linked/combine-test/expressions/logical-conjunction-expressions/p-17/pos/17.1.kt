// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 16 -> sentence 16
 *                expressions, function-literals, lambda-literals -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: false && skips lambda invoke
 */

// TESTCASE NUMBER: 1
var n = 0
val f = { n++; true }
fun test(): Boolean = false && f()
fun check(): Int = n

fun box(): String {
    n = 0
    if (test()) return "NOK"
    if (check() != 0) return "NOK"
    return "OK"
}
