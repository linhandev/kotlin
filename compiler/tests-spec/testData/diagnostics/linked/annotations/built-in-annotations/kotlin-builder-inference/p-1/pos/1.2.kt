// FIR_IDENTICAL
// LANGUAGE: +UnrestrictedBuilderInference
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-builder-inference -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: BuilderInference may be applied to function parameter
 */

// TESTCASE NUMBER: 1
annotation class BuilderInference17747

class Builder17747<T> {
    fun add(t: T) {}
}

fun <S> build17747(@BuilderInference17747 g: Builder17747<S>.() -> Unit): List<S> = emptyList()
