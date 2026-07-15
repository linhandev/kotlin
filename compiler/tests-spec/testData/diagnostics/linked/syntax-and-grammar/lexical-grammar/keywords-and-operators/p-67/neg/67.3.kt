// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 67 -> sentence 67
 * NUMBER: 3
 * DESCRIPTION: Incomplete class declaration CLASS without identifier causes parser error
 */

// TESTCASE NUMBER: 1
class<!SYNTAX!><!>

fun case1(): String {
    return "OK"
}
