// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 128 -> sentence 128
 * NUMBER: 3
 * DESCRIPTION: Incomplete ACTUAL function declaration without name causes compile error
 */

// TESTCASE NUMBER: 1
<!WRONG_MODIFIER_TARGET!>actual<!> fun<!SYNTAX!><!>

fun case1(): String = "OK"
