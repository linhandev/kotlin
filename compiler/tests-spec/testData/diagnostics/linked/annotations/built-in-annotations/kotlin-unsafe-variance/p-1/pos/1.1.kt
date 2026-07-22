// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-unsafe-variance -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: UnsafeVariance on type parameter usage is allowed where specified
 */

// TESTCASE NUMBER: 1
class OutBox17681<out T> {
    fun store(value: @UnsafeVariance T) {}
}

// TESTCASE NUMBER: 2
class InBox17681<in T> {
    fun read(): @UnsafeVariance T = TODO()
}
