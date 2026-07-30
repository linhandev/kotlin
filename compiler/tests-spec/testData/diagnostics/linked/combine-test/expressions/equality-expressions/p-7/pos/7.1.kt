// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 7 -> sentence 7
 *                type-inference, smart-casts -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: == null else uppercase infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: String?) {
    checkSubtype<String>(if (x == null) "nil" else x.uppercase())
}
