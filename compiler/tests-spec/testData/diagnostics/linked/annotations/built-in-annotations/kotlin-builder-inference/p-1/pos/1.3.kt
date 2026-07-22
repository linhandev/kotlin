// FIR_IDENTICAL
// LANGUAGE: +UnrestrictedBuilderInference
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-builder-inference -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: builder-style type inference infers type argument from lambda body
 */

// TESTCASE NUMBER: 1
annotation class BuilderInference17746

class GenericController17746<T> {
    fun yield(t: T) {}
}

@BuilderInference17746
fun <S> generate17746(g: GenericController17746<S>.() -> Unit): List<S> = emptyList()

fun use17746(): List<Int> = generate17746 { yield(42) }
