// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: equals is false when primary constructor property values differ
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Key(val a: Int)

fun case_1() {
    checkSubtype<Boolean>(Key(1) == Key(2))
}
