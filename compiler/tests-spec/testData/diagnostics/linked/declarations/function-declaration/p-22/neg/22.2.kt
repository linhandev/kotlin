// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 2
 * DESCRIPTION: inline functional parameters cannot be passed to noinline parameter positions
 */

// TESTCASE NUMBER: 1
inline fun <R> passInlineToNoinline(p: () -> R) {
    acceptNoinline(<!USAGE_IS_NOT_INLINABLE!>p<!>)
}

inline fun <R> acceptNoinline(noinline p: () -> R) {
    p()
}
