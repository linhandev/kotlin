// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: inline parameters cannot be passed to noinline positions or captured in lambdas passed to non-inline functions
 */

// TESTCASE NUMBER: 1
inline fun noinlineParameter(noinline arg: () -> Unit) {
    arg()
}

inline fun rejectInlineAsNoinline(inl: () -> Unit) {
    noinlineParameter(<!USAGE_IS_NOT_INLINABLE!>inl<!>)
}

// TESTCASE NUMBER: 2
fun bar(value: Any?) {}

inline fun rejectInlineAsValue(inl: () -> Unit) {
    bar(<!USAGE_IS_NOT_INLINABLE!>inl<!>)
}

// TESTCASE NUMBER: 3
inline fun rejectInlineCapture(inl: () -> Unit) {
    bar({ <!NON_LOCAL_RETURN_NOT_ALLOWED!>inl<!>() })
}
