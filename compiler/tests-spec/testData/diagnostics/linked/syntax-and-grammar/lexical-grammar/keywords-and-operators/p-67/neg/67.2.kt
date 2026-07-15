// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 67 -> sentence 67
 * NUMBER: 2
 * DESCRIPTION: Space inside CLASS token as cla ss breaks class declaration lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>cla<!> <!SYNTAX!>ss<!> <!SYNTAX!>Broken67<!>

fun case1(): String {
    return "OK"
}
