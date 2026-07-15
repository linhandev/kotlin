// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 85 -> sentence 85
 * NUMBER: 2
 * DESCRIPTION: Space inside TRY token as tr y breaks try statement lexeme
 */

// TESTCASE NUMBER: 1
fun brokenTry85(): String = <!UNRESOLVED_REFERENCE!>tr<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>y<!> { "OK" }

fun case1(): String = "OK"
