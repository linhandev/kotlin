// FIR_IDENTICAL
// LANGUAGE: +ForbidExposingLessVisibleTypesInInline
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: public inline functions cannot access less visible entities without PublishedApi
 */

// TESTCASE NUMBER: 1
private fun privateHelper(): Int = 1

public inline fun exposePrivate(): Int = <!NON_PUBLIC_CALL_FROM_PUBLIC_INLINE!>privateHelper<!>()

// TESTCASE NUMBER: 2
internal fun internalHelper(): Int = 2

public inline fun exposeInternal(): Int = <!NON_PUBLIC_CALL_FROM_PUBLIC_INLINE!>internalHelper<!>()
