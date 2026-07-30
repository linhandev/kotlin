// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 50 -> sentence 50
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 50 -> sentence 50
 * NUMBER: 1
 * DESCRIPTION: type argument count mismatch with declaration causes compile error
 */

// TESTCASE NUMBER: 1
fun <T> id(x: T): T = x

fun test() = id<!WRONG_NUMBER_OF_TYPE_ARGUMENTS!><Int, String><!>(1)
