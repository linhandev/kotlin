// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: inline parameters with inl/crossinline/noinline modifiers follow allowed propagation rules
 */

// TESTCASE NUMBER: 1
fun bar(value: Any?) {}

inline fun inlineParameter(arg: () -> Unit) {
    arg()
}

inline fun noinlineParameter(noinline arg: () -> Unit) {
    arg()
}

inline fun crossinlineParameter(crossinline arg: () -> Unit) {
    arg()
}

inline fun foo(
    inl: () -> Unit,
    crossinline cinl: () -> Unit,
    noinline noinl: () -> Unit,
) {
    inl()
    cinl()
    noinl()
    inlineParameter(inl)
    inlineParameter(cinl)
    inlineParameter(noinl)
    noinlineParameter(noinl)
    crossinlineParameter(cinl)
    crossinlineParameter(noinl)
    bar(noinl)
    bar({ cinl() })
    bar({ noinl() })
}

fun useFoo() {
    foo({}, {}, {})
}
