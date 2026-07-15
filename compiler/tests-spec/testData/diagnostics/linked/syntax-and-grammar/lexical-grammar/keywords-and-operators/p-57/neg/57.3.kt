// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 57 -> sentence 57
 * NUMBER: 3
 * DESCRIPTION: Incomplete field annotation @field: missing annotation name causes parser error
 */

// TESTCASE NUMBER: 1
class BrokenFieldColon57 {
    <!SYNTAX!>@field:<!>
    var x = 1
}

fun case1(): String {
    return "OK"
}
