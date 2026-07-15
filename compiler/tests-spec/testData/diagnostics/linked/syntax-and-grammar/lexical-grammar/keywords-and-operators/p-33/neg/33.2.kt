// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 33 -> sentence 33
 * NUMBER: 2
 * DESCRIPTION: DOUBLE_SEMICOLON token in incomplete declaration val x = ;; causes parser error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val x =<!SYNTAX!><!> <!SYNTAX!>;;<!>
    return "OK"
}
