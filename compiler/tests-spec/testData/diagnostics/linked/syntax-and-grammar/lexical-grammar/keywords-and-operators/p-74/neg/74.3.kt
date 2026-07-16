// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 74 -> sentence 74
 * NUMBER: 3
 * DESCRIPTION: Incomplete constructor declaration CONSTRUCTOR without parameter list causes parser error
 */

// TESTCASE NUMBER: 1
class BrokenCtor74 {
    constructor<!SYNTAX!><!>
}

fun case1(): String {
    return "OK"
}
