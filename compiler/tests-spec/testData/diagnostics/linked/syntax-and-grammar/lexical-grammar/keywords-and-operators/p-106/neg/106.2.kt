// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 106 -> sentence 106
 * NUMBER: 2
 * DESCRIPTION: Space inside ENUM token as en um breaks enum declaration lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>en<!> <!SYNTAX!>um<!> class BrokenEnum106

fun case1(): String = "OK"
