// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 4 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: shebangLine optional file invalid extra token at start
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>extra<!>

val case1: Int = 1
