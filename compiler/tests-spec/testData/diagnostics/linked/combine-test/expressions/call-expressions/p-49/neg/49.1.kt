// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 49 -> sentence 49
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 49 -> sentence 49
 *                syntax-and-grammar, syntax-grammar -> paragraph 49 -> sentence 49
 * NUMBER: 1
 * DESCRIPTION: type arguments on non-generic function cause compile error
 */

// TESTCASE NUMBER: 1
fun f(x: Int): Int = x

fun test() = f<!WRONG_NUMBER_OF_TYPE_ARGUMENTS!><Int><!>(1)
