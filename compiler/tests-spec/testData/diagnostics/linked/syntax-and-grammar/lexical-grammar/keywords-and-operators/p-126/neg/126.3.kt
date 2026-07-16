// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 126 -> sentence 126
 * NUMBER: 3
 * DESCRIPTION: REIFIED token without inline function causes compile error
 */

// TESTCASE NUMBER: 1
fun <<!REIFIED_TYPE_PARAMETER_NO_INLINE!>reified<!> T> brokenReified126(): String = "OK"

fun case1(): String = "OK"
