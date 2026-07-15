// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: SEMICOLON token in incomplete variable declaration causes parser error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val x =<!SYNTAX!><!> ;
    return "OK"
}
