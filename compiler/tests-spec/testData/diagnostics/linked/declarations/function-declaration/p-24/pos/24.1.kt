// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: noinline parameters may be passed to other functions as noinline or crossinline arguments
 */

// TESTCASE NUMBER: 1
inline fun acceptNoinline(noinline block: () -> Unit) {
    block()
}

inline fun acceptCrossinline(crossinline block: () -> Unit) {
    block()
}

inline fun passNoinlineParameter(noinline block: () -> Unit) {
    acceptNoinline(block)
    acceptCrossinline(block)
}

fun usePassNoinlineParameter() {
    passNoinlineParameter { }
}
