// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 20 -> sentence 20
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: is T check is illegal due to type erasure
 */

// TESTCASE NUMBER: 1
class Box<T> { fun check(x: Any): Boolean = x is <!CANNOT_CHECK_FOR_ERASED!>T<!> }
