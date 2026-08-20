// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 30 -> sentence 30
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 30 -> sentence 30
 * NUMBER: 1
 * DESCRIPTION: wrong number of type arguments at use site
 */

// TESTCASE NUMBER: 1
class Box<T>(val v: T)

fun test() = Box<!WRONG_NUMBER_OF_TYPE_ARGUMENTS!><Int, String><!>(1)
