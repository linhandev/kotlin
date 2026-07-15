// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: Space between ? and . in safe call x ? .length breaks QUEST_NO_WS safeNav
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val s: String? = "abc"
    val len = s <!SYNTAX!>? .length<!>
}
