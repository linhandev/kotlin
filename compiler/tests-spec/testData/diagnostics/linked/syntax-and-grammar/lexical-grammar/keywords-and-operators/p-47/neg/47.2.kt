// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 47 -> sentence 47
 * NUMBER: 2
 * DESCRIPTION: Space in AS_SAFE token as ? breaks AS_SAFE lexeme
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val obj: Any = "x"
    val s = obj as <!SYNTAX!>?<!> <!DEBUG_INFO_MISSING_UNRESOLVED!>String<!><!SYNTAX!><!>
}
