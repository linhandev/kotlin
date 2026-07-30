// FIR_IDENTICAL
// LANGUAGE: +DataObjects
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 100 -> sentence 100
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 100 -> sentence 100
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 100 -> sentence 100
 * NUMBER: 1
 * DESCRIPTION: data object == unrelated class via Any infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data object MyObj
class Other

fun case1() {
    checkSubtype<Boolean>((MyObj as Any) == Other())
}
