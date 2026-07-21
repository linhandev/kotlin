// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, type-parameter-variance -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: classifier type parameters may be declared covariant, contravariant, or invariant
 */

// TESTCASE NUMBER: 1
interface Out<out T> {
    fun produce(): T
}

// TESTCASE NUMBER: 2
interface In<in T> {
    fun consume(value: T)
}

// TESTCASE NUMBER: 3
class Inv<T> {
    fun read(): T = TODO()
    fun write(value: T) {}
}
