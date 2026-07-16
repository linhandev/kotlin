// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 100 -> sentence 100
 * NUMBER: 2
 * DESCRIPTION: Space inside OUT token as ou t breaks variance lexeme
 */

// TESTCASE NUMBER: 1
interface BrokenOut100<ou <!SYNTAX!>t<!> <!SYNTAX!>T<!><!SYNTAX!>><!>

fun case1(): String = "OK"
