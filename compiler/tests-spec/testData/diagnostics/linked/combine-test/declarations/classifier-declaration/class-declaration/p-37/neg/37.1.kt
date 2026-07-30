// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 37 -> sentence 37
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: type argument satisfies only part of where constraints
 */

// TESTCASE NUMBER: 1
class TextRepo<T> where T : CharSequence, T : Comparable<T>

fun test() = TextRepo<<!UPPER_BOUND_VIOLATED!>Int<!>>()
