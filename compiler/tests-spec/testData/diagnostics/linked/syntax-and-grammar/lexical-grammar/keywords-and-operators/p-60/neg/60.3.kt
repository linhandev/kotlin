// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 60 -> sentence 60
 * NUMBER: 3
 * DESCRIPTION: Incomplete property setter SET without parameter list causes parser error
 */

// TESTCASE NUMBER: 1
var incompleteSet60: Int = 0
    set<!SYNTAX!><!> <!SYNTAX, SYNTAX!><!>{
        field = 0
    }

fun case1(): String {
    return "OK"
}
