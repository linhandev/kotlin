// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-builder-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: stdlib BuilderInference requires opt-in in Kotlin 1.9+
 */

// TESTCASE NUMBER: 1
class Builder17742<T> {
    fun add(t: T) {}
}

fun <S> build17742(@<!OPT_IN_USAGE_ERROR!>BuilderInference<!> g: Builder17742<S>.() -> Unit): List<S> = emptyList()
