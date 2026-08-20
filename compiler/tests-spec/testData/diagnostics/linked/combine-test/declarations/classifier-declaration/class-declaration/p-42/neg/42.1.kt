// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 42 -> sentence 42
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 42 -> sentence 42
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 42 -> sentence 42
 * NUMBER: 1
 * DESCRIPTION: where A colon B violated by String and Number
 */

// TESTCASE NUMBER: 1
class Rel<A, B> where A : B

fun test() = Rel<String, <!UPPER_BOUND_VIOLATED!>Number<!>>()
