// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 3 -> sentence 3
 *                expressions, logical-conjunction-expressions -> paragraph 3 -> sentence 3
 *                type-inference, smart-casts -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: conditional expression with && in condition and is smart cast type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "test"
    checkSubtype<Boolean>(if (x is String && x.length > 3) true else false)
}