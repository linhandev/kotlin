// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, object-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: named object cannot be declared locally unlike object literals
 */

// TESTCASE NUMBER: 1
fun foo() {
    <!LOCAL_OBJECT_NOT_ALLOWED!>object LocalNamed<!>
}
