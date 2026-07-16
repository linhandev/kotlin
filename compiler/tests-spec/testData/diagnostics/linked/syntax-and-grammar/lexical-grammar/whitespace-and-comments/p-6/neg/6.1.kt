// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: Vertical Tab (U+000B) used where only WS is allowed
 */

// TESTCASE NUMBER: 1
fun case1(): String {
    val<!SYNTAX!><!><!SYNTAX!>x = 1<!>
    return "OK"
}
