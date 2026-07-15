// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 76 -> sentence 76
 * NUMBER: 3
 * DESCRIPTION: Incomplete companion declaration COMPANION without object keyword causes parser error
 */

// TESTCASE NUMBER: 1
class BrokenCompanionOnly76 {
    companion<!SYNTAX!><!>
}

fun case1(): String {
    return "OK"
}
