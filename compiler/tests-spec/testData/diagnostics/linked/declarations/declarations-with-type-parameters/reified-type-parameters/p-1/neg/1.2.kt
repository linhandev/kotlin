// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE -NOT_YET_SUPPORTED_IN_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: nested reified type parameters require the enclosing function to be inline
 */

// TESTCASE NUMBER: 1
inline fun <reified T> outer() {
    fun <<!REIFIED_TYPE_PARAMETER_NO_INLINE!>reified<!> R> inner() {}
}
