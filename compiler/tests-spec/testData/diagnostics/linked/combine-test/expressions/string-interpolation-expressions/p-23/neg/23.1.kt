// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: empty ${} interpolation expression reports syntax error
 */

// TESTCASE NUMBER: 1
fun test(): String = "empty=${<!SYNTAX!><!>}"
