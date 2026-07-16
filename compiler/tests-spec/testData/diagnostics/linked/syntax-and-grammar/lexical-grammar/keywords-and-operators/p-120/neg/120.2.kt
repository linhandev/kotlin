// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 120 -> sentence 120
 * NUMBER: 2
 * DESCRIPTION: Space inside OPEN token as op en breaks open modifier lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>op<!> <!SYNTAX!>en<!> class BrokenOpen120

fun case1(): String = "OK"
