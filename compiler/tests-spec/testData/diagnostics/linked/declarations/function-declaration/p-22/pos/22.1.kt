// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: inline functions may declare crossinline and noinline functional parameters and call them directly
 */

// TESTCASE NUMBER: 1
inline fun withCrossinlineAndNoinline(crossinline x: () -> Unit, noinline y: () -> Unit) {
    x()
    y()
}

fun useCrossinlineAndNoinline() {
    withCrossinlineAndNoinline({ }, { })
}
