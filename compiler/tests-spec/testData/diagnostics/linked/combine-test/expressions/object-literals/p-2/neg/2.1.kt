// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, object-literals -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: declarations, classifier-declaration, interface-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: object literal must implement all abstract interface members
 */

// TESTCASE NUMBER: 1
interface Click {
    fun onClick()
}

fun case_1() = <!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>object<!> : Click {}
