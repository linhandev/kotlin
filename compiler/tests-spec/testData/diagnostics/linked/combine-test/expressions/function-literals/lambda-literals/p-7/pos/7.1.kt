// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 7 -> sentence 7
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: forEach higher-order call with destructuring parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(ps: List<Pair<Int, Int>>) {
    var s = 0
    ps.forEach { (a, b) -> s += a + b }
    checkSubtype<Int>(s)
}
