// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 78 -> sentence 78
 * NUMBER: 2
 * DESCRIPTION: Space inside THIS token as th is breaks this expression lexeme
 */

// TESTCASE NUMBER: 1
class BrokenThis78 {
    fun value(): String = <!UNRESOLVED_REFERENCE!>th<!> is<!SYNTAX!><!SYNTAX!><!>.<!><!SYNTAX!>token<!>
}

fun case1(): String {
    return "OK"
}
