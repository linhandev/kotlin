// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 123 -> sentence 123
 * NUMBER: 3
 * DESCRIPTION: Incomplete VARARG parameter without type causes parser error
 */

// TESTCASE NUMBER: 1
fun brokenVararg123(<!VALUE_PARAMETER_WITH_NO_TYPE_ANNOTATION!><!FORBIDDEN_VARARG_PARAMETER_TYPE!>vararg<!> items<!>): String = "OK"

fun case1(): String = "OK"
