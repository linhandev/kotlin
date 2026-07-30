// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 95 -> sentence 95
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 95 -> sentence 95
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 95 -> sentence 95
 * NUMBER: 1
 * DESCRIPTION: data class copy equals original
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Data(val x: Int)

fun case1() {
    checkSubtype<Boolean>(Data(42) == Data(42).copy())
}
