// FIR_IDENTICAL
// LANGUAGE: +DataObjects
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 78 -> sentence 78
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 78 -> sentence 78
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 78 -> sentence 78
 * NUMBER: 1
 * DESCRIPTION: data object == itself infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data object MyObject

fun case1() {
    checkSubtype<Boolean>(MyObject == MyObject)
}
