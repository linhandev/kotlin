// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, whitespace-and-comments -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Unicode escape \\u00D with only 3 hex digits (requires 4)
 */

// TESTCASE NUMBER: 1
fun case1() {
    val c = '<!ILLEGAL_ESCAPE!>\u00D<!>'
}
