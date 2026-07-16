// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 32 -> sentence 32
 * NUMBER: 2
 * DESCRIPTION: COLONCOLON token without member name String:: causes parser error
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val ref = String::
    <!SYNTAX!><!>return "OK"
}
