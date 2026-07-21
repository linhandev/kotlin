// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: crossinline parameters cannot be stored in variables
 */

// TESTCASE NUMBER: 1
inline fun storeCrossinline(crossinline block: () -> Unit) {
    val saved = <!USAGE_IS_NOT_INLINABLE!>block<!>
    saved()
}
