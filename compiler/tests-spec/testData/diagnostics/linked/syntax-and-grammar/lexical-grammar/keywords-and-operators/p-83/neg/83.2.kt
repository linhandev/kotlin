// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 83 -> sentence 83
 * NUMBER: 2
 * DESCRIPTION: Space inside ELSE token as el se breaks else branch lexeme
 */

// TESTCASE NUMBER: 1
fun brokenElse83(flag: Boolean): String = <!TYPE_MISMATCH!><!INVALID_IF_AS_EXPRESSION!>if<!> (flag) "OK" <!UNRESOLVED_REFERENCE!>el<!> <!UNRESOLVED_REFERENCE!>se<!><!> <!SYNTAX!>"<!><!SYNTAX!>NOK<!><!SYNTAX!>"<!>

fun case1(): String {
    return "OK"
}
