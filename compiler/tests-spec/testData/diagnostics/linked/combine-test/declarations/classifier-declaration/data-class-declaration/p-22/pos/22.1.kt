// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: declarations, classifier-declaration, data-class-declaration -> paragraph 22 -> sentence 22
 *                declarations, function-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: data class with default primary constructor parameters still generates copy
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class Item(val id: Int, val active: Boolean = true)

fun case_1() {
    checkSubtype<Boolean>(Item(1).copy().active)
}
