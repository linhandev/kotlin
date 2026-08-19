// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 18 -> sentence 18
 *                type-system, nullable-types -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: nullable Pair as whole argument can be destructured via safe call
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(p: Pair<Int?, String?>?) {
    val r = p?.let { (a, b) -> (a ?: 0) + (b?.length ?: 0) }
    checkSubtype<Int?>(r)
}
