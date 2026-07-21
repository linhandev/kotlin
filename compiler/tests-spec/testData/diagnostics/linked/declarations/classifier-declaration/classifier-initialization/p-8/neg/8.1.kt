// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, classifier-initialization -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: member function declaration without name inside classifier initialization scope
 */

// TESTCASE NUMBER: 1
class Holder {
    <!FUNCTION_DECLARATION_WITH_NO_NAME!>fun ()<!> {}
}
