// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: invariant usages, UnsafeVariance and extensions are allowed where specified
 */

// TESTCASE NUMBER: 1
class OutBox<out T> {
    fun store(value: @UnsafeVariance T) {}
}

// TESTCASE NUMBER: 2
class InBox<in T> {
    fun read(): @UnsafeVariance T = TODO()
}

// TESTCASE NUMBER: 3
class Inv<T>

class InvBox<T> {
    fun produce(): T = TODO()
    fun consume(value: T) {}
    fun wrap(out: OutBox<T>) {}
    fun sink(input: InBox<T>) {}
}

// TESTCASE NUMBER: 4
fun <T> OutBox<T>.extensionRead(): T = TODO()
