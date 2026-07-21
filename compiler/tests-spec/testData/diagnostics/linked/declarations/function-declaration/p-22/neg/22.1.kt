// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: crossinline parameters forbid non-local returns in lambda arguments
 */

// TESTCASE NUMBER: 1
inline fun invokeCrossinline(crossinline block: () -> Unit) {
    block()
}

fun nonLocalReturnInCrossinline() {
    invokeCrossinline {
        <!RETURN_NOT_ALLOWED!>return<!>
    }
}
