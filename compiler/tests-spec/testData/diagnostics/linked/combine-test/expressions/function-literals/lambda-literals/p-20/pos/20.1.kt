// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: destructuring parameter remains single-arg inside receiver lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(ps: MutableList<Pair<Int, Int>>) {
    var s = 0
    ps.apply { forEach { (a, b) -> s += a + b } }
    checkSubtype<Int>(s)
}
