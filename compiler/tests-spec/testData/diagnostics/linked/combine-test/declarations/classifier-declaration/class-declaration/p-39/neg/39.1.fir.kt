// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 39 -> sentence 39
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: Node does not satisfy recursive Comparable upper bound
 */

// TESTCASE NUMBER: 1
open class Node

class Ordered<T : Comparable<T>>(a: T, b: T)

fun test() = <!INAPPLICABLE_CANDIDATE!>Ordered<!><<!UPPER_BOUND_VIOLATED!>Node<!>>(Node(), Node())
