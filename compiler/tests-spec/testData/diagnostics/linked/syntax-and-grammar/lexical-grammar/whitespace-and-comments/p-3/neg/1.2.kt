// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 3 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Shebang not at the beginning of file
 */

// TESTCASE NUMBER: 1
package test

<!SYNTAX!>#<!><!SYNTAX!>!<!><!SYNTAX!>/<!><!SYNTAX!>usr<!><!SYNTAX!>/<!><!SYNTAX!>bin<!><!SYNTAX!>/<!><!SYNTAX!>env<!> <!SYNTAX!>kotlin<!>

fun case1(): String { return "OK" }
