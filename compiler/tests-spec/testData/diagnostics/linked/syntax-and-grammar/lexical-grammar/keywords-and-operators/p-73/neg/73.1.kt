// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 73 -> sentence 73
 * NUMBER: 1
 * DESCRIPTION: Standalone TYPE_ALIAS token as statement causes compile error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    typealias
    <!SYNTAX!>return<!> <!SYNTAX!>"<!><!SYNTAX!>OK<!><!SYNTAX!>"<!>
}
