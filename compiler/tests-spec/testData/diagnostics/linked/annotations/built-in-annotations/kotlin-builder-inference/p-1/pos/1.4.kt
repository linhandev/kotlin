// FIR_IDENTICAL
// WITH_STDLIB
// OPT_IN: kotlin.experimental.ExperimentalTypeInference
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-builder-inference -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: stdlib BuilderInference with OptIn compiles on function parameter
 */

// TESTCASE NUMBER: 1
class Builder17748<T> {
    fun add(t: T) {}
}

fun <S> build17748(@BuilderInference g: Builder17748<S>.() -> Unit): List<S> = emptyList()

fun use17748(): List<String> = build17748 { add("x") }
