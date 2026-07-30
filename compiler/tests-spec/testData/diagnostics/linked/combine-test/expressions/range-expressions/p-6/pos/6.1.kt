// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 6 -> sentence 6
 *                expressions, comparison-expressions -> paragraph 6 -> sentence 6
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: downTo + step infers Boolean / List
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(1 in 3 downTo 1 && 4 !in 3 downTo 1)
    checkSubtype<List<Int>>((5 downTo 1 step 2).toList())
}
