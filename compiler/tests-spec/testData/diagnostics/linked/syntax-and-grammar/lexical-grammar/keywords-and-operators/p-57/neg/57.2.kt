// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 57 -> sentence 57
 * NUMBER: 2
 * DESCRIPTION: Incomplete field annotation @field without colon causes parser error
 */

// TESTCASE NUMBER: 1
class BrokenField57 {
    @field<!SYNTAX!><!> Suppress("WARNING")
    var x = 1
}

fun case1(): String {
    return "OK"
}
