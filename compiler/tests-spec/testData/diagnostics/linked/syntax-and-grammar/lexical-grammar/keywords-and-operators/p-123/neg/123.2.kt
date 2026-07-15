// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 123 -> sentence 123
 * NUMBER: 2
 * DESCRIPTION: Space inside VARARG token as var arg breaks vararg modifier lexeme
 */

// TESTCASE NUMBER: 1
fun brokenVararg123(<!VALUE_PARAMETER_WITH_NO_TYPE_ANNOTATION!><!VAL_OR_VAR_ON_FUN_PARAMETER!>var<!> arg<!><!SYNTAX!><!> items: String): String = items

fun case1(): String = "OK"
