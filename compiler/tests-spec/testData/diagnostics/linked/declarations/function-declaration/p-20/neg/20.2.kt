// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 20 -> sentence 20
 * NUMBER: 2
 * DESCRIPTION: crossinline parameters cannot be passed to non-crossinline inline parameters
 */

// TESTCASE NUMBER: 1
inline fun <R> toOnlyLocal(crossinline body: () -> R) {
    body()
}

inline fun <R> inlineAll(body: () -> R) {
    toOnlyLocal(<!NON_LOCAL_RETURN_NOT_ALLOWED!>body<!>)
}
