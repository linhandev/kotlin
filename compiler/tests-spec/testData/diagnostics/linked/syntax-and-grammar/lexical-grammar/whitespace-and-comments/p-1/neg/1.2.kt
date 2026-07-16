// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Invalid hex digits in unicode escape
 */

// TESTCASE NUMBER: 1
fun case1() {
    val c = '<!ILLEGAL_ESCAPE!>\uGGGG<!>'
}
