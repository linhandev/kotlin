// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: kotlinFile shebangLine not at file beginning violates order
 */

// TESTCASE NUMBER: 1
// not first line
<!SYNTAX!>#<!><!SYNTAX!>!<!><!SYNTAX!>/<!><!SYNTAX!>usr<!><!SYNTAX!>/<!><!SYNTAX!>bin<!><!SYNTAX!>/<!><!SYNTAX!>env<!> <!SYNTAX!>kotlin<!>

<!SYNTAX!>package<!> <!SYNTAX!>tokens<!><!SYNTAX!>.<!><!SYNTAX!>spec<!><!SYNTAX!>.<!><!SYNTAX!>p1<!>

fun case1(): String = "OK"
