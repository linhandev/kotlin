// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-inference, bare-type-argument-inference -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: is-check with specific type arguments fails because Fee<String, Int> cannot be checked for erased type
 */

// TESTCASE NUMBER: 1
interface Foo<A, B>
class Fee<T, U>(val first: T, val second: U) : Foo<U, T>

fun case_1() {
    val foo: Foo<String, Int> = Fee(42, "hello")
    if (foo is <!CANNOT_CHECK_FOR_ERASED!>Fee<String, Int><!>) {
        println("never")
    }
}
