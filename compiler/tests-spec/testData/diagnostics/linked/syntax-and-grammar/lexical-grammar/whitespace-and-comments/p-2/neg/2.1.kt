// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Invalid hex digits in unicode escape for CR character
 */

// TESTCASE NUMBER: 1
fun case1() {
    val c = '<!ILLEGAL_ESCAPE!>\u000G<!>'
}
