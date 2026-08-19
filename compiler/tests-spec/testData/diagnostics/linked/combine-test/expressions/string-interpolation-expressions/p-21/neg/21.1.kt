// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: unresolved identifier in simple interpolation reports error
 */

// TESTCASE NUMBER: 1
fun test(): String = "x=$<!UNRESOLVED_REFERENCE!>missing<!>"
