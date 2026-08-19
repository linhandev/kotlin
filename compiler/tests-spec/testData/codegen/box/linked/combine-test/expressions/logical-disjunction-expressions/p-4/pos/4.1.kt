// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-disjunction-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: || Boolean result with short-circuit vs evaluate-right paths
 */

// TESTCASE NUMBER: 1
var n = 0
fun right(): Boolean {
    n++
    return true
}

fun test(flag: Boolean): Boolean {
    n = 0
    return flag || right()
}

fun box(): String {
    if (!test(true)) return "NOK: short value"
    if (n != 0) return "NOK: short side"
    if (!test(false)) return "NOK: eval value"
    if (n != 1) return "NOK: eval side"
    return "OK"
}
