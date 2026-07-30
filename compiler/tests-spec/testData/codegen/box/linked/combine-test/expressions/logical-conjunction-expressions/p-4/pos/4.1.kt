// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: && short-circuits before side-effecting throw call
 */

// TESTCASE NUMBER: 1
var n = 0
fun boom(): Boolean {
    n++
    throw IllegalStateException()
}

fun test(): Boolean = false && boom()

fun box(): String {
    if (test()) return "NOK: value"
    if (n != 0) return "NOK: side-effect"
    return "OK"
}
