// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: data object equality works without componentN
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data object Empty

fun case_1() {
    checkSubtype<Boolean>(Empty == Empty)
}
