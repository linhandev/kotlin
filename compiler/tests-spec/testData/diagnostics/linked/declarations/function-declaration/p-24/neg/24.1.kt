// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: inline lambda cannot be passed to noinline parameter
 */

// TESTCASE NUMBER: 1
inline fun noinlineConsumer(noinline block: () -> Unit) {
    block()
}

inline fun passInline(inl: () -> Unit) {
    noinlineConsumer(<!USAGE_IS_NOT_INLINABLE!>inl<!>)
}
