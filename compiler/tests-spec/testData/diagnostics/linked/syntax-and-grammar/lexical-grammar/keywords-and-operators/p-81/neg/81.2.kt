// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 81 -> sentence 81
 * NUMBER: 2
 * DESCRIPTION: Space inside WHERE token as wh ere breaks where clause lexeme
 */

// TESTCASE NUMBER: 1
<!NON_MEMBER_FUNCTION_NO_BODY!>fun <T> broken81(value: T): T<!> <!SYNTAX!>wh<!> <!SYNTAX!>ere<!> <!SYNTAX!>T<!> <!SYNTAX!>:<!> <!SYNTAX!>Any<!> <!SYNTAX!>=<!> <!WRONG_MODIFIER_TARGET!>value<!>

fun case1(): String {
    return "OK"
}
