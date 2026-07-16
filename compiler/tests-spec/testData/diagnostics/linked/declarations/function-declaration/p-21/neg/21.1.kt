// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: reified type parameters are only allowed on inline functions
 */

// TESTCASE NUMBER: 1
fun <<!REIFIED_TYPE_PARAMETER_NO_INLINE!>reified<!> T> notInlineReified(): T = TODO()
