// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: crossinline parameters may be called directly inside an inline function body
 */

// TESTCASE NUMBER: 1
inline fun callCrossinlineDirectly(crossinline block: () -> Unit) {
    block()
}

fun useCrossinlineDirectly() {
    callCrossinlineDirectly { }
}
