// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 35 -> sentence 35
 * NUMBER: 1
 * DESCRIPTION: Space after @ breaks AT_NO_WS before annotation name @ Suppress
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>@<!> <!SYNTAX!>Suppress<!><!SYNTAX!>(<!><!SYNTAX!>"<!><!SYNTAX!>UNUSED_VARIABLE<!><!SYNTAX!>"<!><!SYNTAX!>)<!>
fun case1(): String {
    return "OK"
}
