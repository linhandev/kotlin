// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 96 -> sentence 96
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 96 -> sentence 96
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 96 -> sentence 96
 * NUMBER: 1
 * DESCRIPTION: data class both-null properties equals infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

data class Data(val x: Int?)

fun case1() {
    checkSubtype<Boolean>(Data(null) == Data(null))
}
