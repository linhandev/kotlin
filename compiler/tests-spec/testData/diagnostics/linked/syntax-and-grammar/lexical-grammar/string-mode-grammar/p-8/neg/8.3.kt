// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, string-mode-grammar -> paragraph 8 -> sentence 8
 * NUMBER: 3
 * DESCRIPTION: LineStrExprStart ${ with invalid expression syntax
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val expr = "${<!DEBUG_INFO_MISSING_UNRESOLVED!>+<!><!SYNTAX!><!>}"
    return expr
}
