// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 72 -> sentence 72
 * NUMBER: 2
 * DESCRIPTION: Space inside VAR token as va r breaks var property declaration lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>va<!> <!SYNTAX!>r<!> <!SYNTAX!>broken72<!> <!SYNTAX!>=<!> <!SYNTAX!>1<!>

fun case1(): String {
    return "OK"
}
