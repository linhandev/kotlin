// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 109 -> sentence 109
 * NUMBER: 2
 * DESCRIPTION: Space inside DATA token as da ta breaks data declaration lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>da<!> <!SYNTAX!>ta<!> class BrokenData109

fun case1(): String = "OK"
