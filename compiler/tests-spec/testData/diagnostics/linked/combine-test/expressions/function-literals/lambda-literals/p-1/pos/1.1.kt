// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: lambda parameter can destructure Pair
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(ps: List<Pair<Int, String>>) {
    val r = ps.map { (k, v) -> k + v.length }
    checkSubtype<List<Int>>(r)
}
