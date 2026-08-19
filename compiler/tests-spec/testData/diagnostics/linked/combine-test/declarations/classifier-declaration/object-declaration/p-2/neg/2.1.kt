// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, object-declaration -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: object must implement abstract interface members
 */

// TESTCASE NUMBER: 1
interface Click {
    fun onClick()
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>object Bad<!> : Click

fun case_1() = Bad.onClick()
