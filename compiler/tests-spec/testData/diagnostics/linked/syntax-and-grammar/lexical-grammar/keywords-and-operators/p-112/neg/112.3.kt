// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 112 -> sentence 112
 * NUMBER: 3
 * DESCRIPTION: Incomplete OPERATOR function declaration without name causes parser error
 */

// TESTCASE NUMBER: 1
<!INAPPLICABLE_OPERATOR_MODIFIER!>operator<!> fun<!SYNTAX!><!>

fun case1(): String = "OK"
