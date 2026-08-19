// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: copy with no arguments yields an equal instance
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class User(val id: Int)

fun case_1() {
    checkSubtype<Boolean>(User(1).copy() == User(1))
}
