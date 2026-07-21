// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-inference, bare-type-argument-inference -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: bare type argument inference — simple non-nullable T, TC[A0…An] <: T with swapped type parameters
 * HELPERS: checkType
 */

interface Foo144<A, B>
class Fee144<T, U> : Foo144<U, T>

// TESTCASE NUMBER: 1
fun case_1(foo: Foo144<String, Int>) {
    if (foo is Fee144) {
        checkSubtype<Fee144<Int, String>>(<!DEBUG_INFO_SMARTCAST!>foo<!>)
    }
}
