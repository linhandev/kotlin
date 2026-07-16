// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 73 -> sentence 73
 * NUMBER: 2
 * DESCRIPTION: Space inside TYPE_ALIAS token as type alias breaks typealias declaration lexeme
 */

// TESTCASE NUMBER: 1
<!SYNTAX!>type<!> <!SYNTAX!>alias<!> <!SYNTAX!>Broken73<!> <!SYNTAX!>=<!> <!SYNTAX!>Int<!>

fun case1(): String {
    return "OK"
}
