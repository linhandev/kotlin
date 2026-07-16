// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declaration-visibility -> paragraph 7 -> sentence 7
 * NUMBER: 2
 * DESCRIPTION: internal inline function may call internal helper
 */

// TESTCASE NUMBER: 1
internal fun helper(): Int = 1

internal inline fun caller(): Int = helper()
