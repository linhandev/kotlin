// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 51 -> sentence 51
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 51 -> sentence 51
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 51 -> sentence 51
 *                inheritance, inheriting -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: subclass cannot drop parent Number upper bound
 */

// TESTCASE NUMBER: 1
open class Base<T : Number>

class Child<T> : Base<<!UPPER_BOUND_VIOLATED!>T<!>>()
