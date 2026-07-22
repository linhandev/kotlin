// FIR_IDENTICAL
// LANGUAGE: +ForbidExposingLessVisibleTypesInInline
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-published-api -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: public inline function cannot access internal entity without PublishedApi
 */

// TESTCASE NUMBER: 1
internal fun internalHelper17731(): Int = 1

public inline fun exposeInternal17731(): Int = <!NON_PUBLIC_CALL_FROM_PUBLIC_INLINE!>internalHelper17731<!>()
