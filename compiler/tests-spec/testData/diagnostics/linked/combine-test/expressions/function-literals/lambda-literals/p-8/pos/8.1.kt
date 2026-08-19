// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: underscore skips a destructuring component in a lambda
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(ps: List<Pair<Int, String>>) {
    val r = ps.map { (k, _) -> k }
    checkSubtype<List<Int>>(r)
}
