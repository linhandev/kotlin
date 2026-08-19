// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 1 -> sentence 1
 *                type-inference, smart-casts -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: conditional expression with is smart cast type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "test"
    checkSubtype<Int>(if (x is String) x.length else -1)
}

// TESTCASE NUMBER: 2
fun case2() {
    val x: Any = 123
    checkSubtype<Int>(if (x is String) x.length else -1)
}
