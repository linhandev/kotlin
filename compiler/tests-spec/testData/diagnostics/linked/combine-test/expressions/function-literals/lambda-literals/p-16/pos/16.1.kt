// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: chained higher-order calls can use consecutive destructuring lambdas
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(xs: List<Pair<Int, Int>>) {
    val r = xs.filter { (a, _) -> a > 0 }.map { (a, b) -> a + b }
    checkSubtype<List<Int>>(r)
}
