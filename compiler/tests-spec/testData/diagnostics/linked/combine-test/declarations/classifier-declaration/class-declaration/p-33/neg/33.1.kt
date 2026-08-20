// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 33 -> sentence 33
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: explicit type argument violates Number upper bound
 */

// TESTCASE NUMBER: 1
class NumBox<T : Number>(val v: T)

fun test() = NumBox<<!UPPER_BOUND_VIOLATED!>String<!>>("s")
