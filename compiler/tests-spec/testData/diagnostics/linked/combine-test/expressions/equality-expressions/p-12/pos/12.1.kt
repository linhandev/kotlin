// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 12 -> sentence 12
 *                type-inference, smart-casts -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: Int? == 1 then smart cast n + 10 infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(n: Int?) {
    checkSubtype<Int>(if (n == 1) n + 10 else -1)
}
