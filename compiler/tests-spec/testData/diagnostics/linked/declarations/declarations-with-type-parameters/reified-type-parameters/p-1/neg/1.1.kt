// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: reified type parameters require inline and cannot be used as runtime types otherwise
 */

// TESTCASE NUMBER: 1
fun <<!REIFIED_TYPE_PARAMETER_NO_INLINE!>reified<!> T> notInlineReified(): T = TODO()

// TESTCASE NUMBER: 2
fun <T> check(value: Any?) {
    if (value is <!CANNOT_CHECK_FOR_ERASED!>T<!>) {}
}
