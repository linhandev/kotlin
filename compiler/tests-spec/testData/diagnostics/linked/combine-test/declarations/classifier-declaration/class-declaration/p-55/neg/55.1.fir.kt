// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 55 -> sentence 55
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 55 -> sentence 55
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 55 -> sentence 55
 * NUMBER: 1
 * DESCRIPTION: member where Comparable violated by Any
 */

// TESTCASE NUMBER: 1
class C<T : Number> { fun <U> f(u: U): U where U : Comparable<U> = u }

fun test() = C<Int>().<!CANNOT_INFER_PARAMETER_TYPE!>f<!>(<!ARGUMENT_TYPE_MISMATCH!>Any()<!>)
