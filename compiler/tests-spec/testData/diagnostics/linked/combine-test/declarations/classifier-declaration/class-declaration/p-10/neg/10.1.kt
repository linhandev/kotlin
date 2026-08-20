// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: out T cannot appear in in-position parameter
 */

// TESTCASE NUMBER: 1
class OutBox<out T>(val value: T) { fun set(x: <!TYPE_VARIANCE_CONFLICT_ERROR!>T<!>) {} }

fun test() = OutBox(1)
