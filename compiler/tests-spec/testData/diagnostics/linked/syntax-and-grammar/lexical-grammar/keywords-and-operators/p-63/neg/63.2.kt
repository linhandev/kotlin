// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 63 -> sentence 63
 * NUMBER: 2
 * DESCRIPTION: Incomplete setparam annotation @setparam without colon on property causes parser error
 */

// TESTCASE NUMBER: 1
class BrokenSetparam63 {
    @setparam<!SYNTAX!><!> Suppress("WARNING")
    var x: Int = 0
}

fun case1(): String {
    return "OK"
}
