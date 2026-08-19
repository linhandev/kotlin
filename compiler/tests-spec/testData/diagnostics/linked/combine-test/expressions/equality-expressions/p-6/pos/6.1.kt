// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 6 -> sentence 6
 *                type-inference, smart-casts -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: != null then length infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: String?) {
    checkSubtype<Int>(if (x != null) x.length else 0)
}
