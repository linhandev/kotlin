// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 5 -> sentence 5
 *                expressions, comparison-expressions -> paragraph 5 -> sentence 5
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: until/..< half-open ranges infer Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Boolean>(10 !in 1..<10 && 10 !in 1 until 10)
    checkSubtype<Boolean>((1 until 5).toList() == (1..<5).toList())
}
