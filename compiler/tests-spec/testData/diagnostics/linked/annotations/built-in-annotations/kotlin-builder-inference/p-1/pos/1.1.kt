// FIR_IDENTICAL
// LANGUAGE: +UnrestrictedBuilderInference
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-builder-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: local BuilderInference annotation on function enables builder-style type inference
 */

// TESTCASE NUMBER: 1
annotation class BuilderInference17741

class Builder17741<T> {
    fun add(t: T) {}
}

@BuilderInference17741
fun <S> build17741(g: Builder17741<S>.() -> Unit): List<S> = emptyList()
