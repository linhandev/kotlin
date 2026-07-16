// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: foo is Fee<String, Int> on Foo<String, Int> reports CANNOT_CHECK_FOR_ERASED
 */

interface Foo<A, B>
class Fee<T, U> : Foo<U, T>

// TESTCASE NUMBER: 1
fun case1(foo: Foo<String, Int>) {
    val x = foo is <!CANNOT_CHECK_FOR_ERASED!>Fee<String, Int><!>
}
