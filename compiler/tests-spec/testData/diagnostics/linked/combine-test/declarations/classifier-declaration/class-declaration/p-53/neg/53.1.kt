// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 53 -> sentence 53
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 53 -> sentence 53
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 53 -> sentence 53
 * NUMBER: 1
 * DESCRIPTION: nested where U colon T violated by String under Number
 */

// TESTCASE NUMBER: 1
class Outer<T> { inner class Inner<U> where U : T {} }

fun test() = Outer<Number>().Inner<<!UPPER_BOUND_VIOLATED!>String<!>>()
