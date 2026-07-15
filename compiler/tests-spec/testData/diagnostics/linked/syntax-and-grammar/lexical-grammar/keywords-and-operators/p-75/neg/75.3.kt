// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 75 -> sentence 75
 * NUMBER: 3
 * DESCRIPTION: Incomplete delegated property BY without delegate expression causes parser error
 */

// TESTCASE NUMBER: 1
class BrokenByMissing75 {
    val token: String by<!SYNTAX!><!>
}

fun case1(): String {
    return "OK"
}
