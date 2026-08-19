// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 6 -> sentence 6
 *                expressions, conditional-expressions -> paragraph 6 -> sentence 6
 *                type-inference, smart-casts -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: nested conditional expression in branch with is smart cast type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val x: Any = "hello"
    checkSubtype<Int>(if (x is String) { if (x.length > 0) x.length else 0 } else -1)
}
