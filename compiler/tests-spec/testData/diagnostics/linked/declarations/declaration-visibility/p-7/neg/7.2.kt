// FIR_IDENTICAL
// LANGUAGE: +ForbidExposingLessVisibleTypesInInline
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 7 -> sentence 7
 * NUMBER: 2
 * DESCRIPTION: public inline cannot access private member without PublishedApi
 */

// TESTCASE NUMBER: 1
class Api {
    private fun secret(): Int = 1
    inline fun expose(): Int = <!NON_PUBLIC_CALL_FROM_PUBLIC_INLINE!>secret<!>()
}
