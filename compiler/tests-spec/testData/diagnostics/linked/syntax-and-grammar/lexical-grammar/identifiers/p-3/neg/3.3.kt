// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: Identifier starting with UnicodeDigit ٤name violates Letter first character rule
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val <!SYNTAX!>٤<!><!SYNTAX!>name = 4<!>
}
