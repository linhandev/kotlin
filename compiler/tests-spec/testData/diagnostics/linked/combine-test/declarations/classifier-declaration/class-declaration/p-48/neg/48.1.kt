// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 48 -> sentence 48
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 48 -> sentence 48
 *                type-system, introduction-1 -> paragraph 48 -> sentence 48
 * NUMBER: 1
 * DESCRIPTION: nullable String question violates non-null Any upper bound
 */

// TESTCASE NUMBER: 1
class Box<T : Any>(val v: T)

fun test() = Box<<!UPPER_BOUND_VIOLATED!>String?<!>>("a")
