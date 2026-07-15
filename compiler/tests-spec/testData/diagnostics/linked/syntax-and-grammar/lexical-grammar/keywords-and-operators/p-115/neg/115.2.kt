// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 115 -> sentence 115
 * NUMBER: 2
 * DESCRIPTION: Space inside EXTERNAL token as exter nal breaks external modifier lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>exter<!> <!SYNTAX!>nal<!> fun brokenExternal115(): String = "OK"

fun case1(): String = "OK"
