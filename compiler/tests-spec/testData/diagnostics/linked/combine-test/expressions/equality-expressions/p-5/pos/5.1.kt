// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: != null infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(s: String?) {
    checkSubtype<Boolean>(s != null)
}
