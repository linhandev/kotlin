// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: call expression cannot use simple $identifier form without ${}
 */

// TESTCASE NUMBER: 1
fun foo(): Int = 1

fun test(): String = "v=$<!FUNCTION_CALL_EXPECTED!>foo<!>()"
