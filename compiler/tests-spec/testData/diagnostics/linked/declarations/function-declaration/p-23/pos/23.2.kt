// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 2
 * DESCRIPTION: crossinline lambda may be invoked inside inline function
 */

// TESTCASE NUMBER: 1
inline fun runCrossinline(crossinline block: () -> Unit) {
    block()
}

fun useCrossinlineInvoke() {
    runCrossinline { }
}
