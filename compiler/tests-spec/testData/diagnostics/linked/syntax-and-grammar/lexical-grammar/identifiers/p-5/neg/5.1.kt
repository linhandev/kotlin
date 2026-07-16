// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, identifiers -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: Hard keyword if used as Identifier without escaping violates IdentifierOrSoftKey rule
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val <!SYNTAX!>if<!> = 1
    return "OK"
}

// TESTCASE NUMBER: 2
fun case2(): String {
    val <!SYNTAX!>when<!> = 2
    return "OK"
}

// TESTCASE NUMBER: 3
fun case3(): String {
    val<!SYNTAX!><!> object <!SYNTAX!>=<!> <!SYNTAX!>3<!>
    return "OK"
}
