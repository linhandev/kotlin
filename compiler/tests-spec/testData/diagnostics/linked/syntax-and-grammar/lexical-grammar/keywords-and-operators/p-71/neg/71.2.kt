// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 71 -> sentence 71
 * NUMBER: 2
 * DESCRIPTION: Space inside VAL token as va l breaks val property declaration lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>va<!> <!SYNTAX!>l<!> <!SYNTAX!>broken71<!> <!SYNTAX!>=<!> <!SYNTAX!>1<!>

fun case1(): String {
    return "OK"
}
