// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 127 -> sentence 127
 * NUMBER: 2
 * DESCRIPTION: Space inside EXPECT token as ex pect breaks expect modifier lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>ex<!> <!SYNTAX!>pect<!> fun brokenExpect127(): String = "OK"

fun case1(): String = "OK"
