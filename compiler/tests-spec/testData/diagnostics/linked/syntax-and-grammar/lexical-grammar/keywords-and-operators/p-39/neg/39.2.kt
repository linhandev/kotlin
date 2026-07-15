// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 39 -> sentence 39
 * NUMBER: 2
 * DESCRIPTION: Space before colon in elvis null ? : 1 breaks QUEST_NO_WS elvis rule
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val x: Int? = null
    val y = x <!SYNTAX!>? : 1<!>
}
