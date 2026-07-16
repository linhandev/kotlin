// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 84 -> sentence 84
 * NUMBER: 2
 * DESCRIPTION: Space inside WHEN token as wh en breaks when expression lexeme
 */

// TESTCASE NUMBER: 1
fun brokenWhen84(flag: Boolean): String = <!UNRESOLVED_REFERENCE!>wh<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>en<!> { <!CANNOT_INFER_PARAMETER_TYPE, NAME_SHADOWING, UNUSED_ANONYMOUS_PARAMETER!>flag<!> -> "OK" <!SYNTAX!>else -> "NOK"<!> }

fun case1(): String {
    return "OK"
}
