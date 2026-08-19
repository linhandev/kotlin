// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 28 -> sentence 28
 *                expressions, elvis-operator-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: == null else branch infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(s: String?) {
    checkSubtype<String>(if (s == null) "nil" else s)
}
