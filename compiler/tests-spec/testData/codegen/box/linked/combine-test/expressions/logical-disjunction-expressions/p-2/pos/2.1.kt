// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-disjunction-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: || short-circuits before side-effecting throw call
 */

// TESTCASE NUMBER: 1
var n = 0
fun boom(): Boolean {
    n++
    throw IllegalStateException()
}

fun test(): Boolean = true || boom()

fun box(): String {
    if (!test()) return "NOK: value"
    if (n != 0) return "NOK: side-effect"
    return "OK"
}
