// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 15 -> sentence 15
 *                declarations, declaration-visibility -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: destructuring private primary constructor component outside the class is invisible
 */

// TESTCASE NUMBER: 1
data class Secret(private val code: Int, val label: String)

fun case_1(s: Secret): Int {
    val (<!INVISIBLE_MEMBER!>c<!>, _) = s
    return <!DEBUG_INFO_ELEMENT_WITH_ERROR_TYPE!>c<!>
}
