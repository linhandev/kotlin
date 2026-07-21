// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, local-class-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: inner modifier is not allowed on local class
 */

// TESTCASE NUMBER: 1
fun bar() {
    <!WRONG_MODIFIER_TARGET!>inner<!> class LocalInner
}
