// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 2 -> sentence 2
 *                expressions, comparison-expressions -> paragraph 2 -> sentence 2
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Char range in infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>('b' in 'a'..'c' && 'd' !in 'a'..'c')
}
