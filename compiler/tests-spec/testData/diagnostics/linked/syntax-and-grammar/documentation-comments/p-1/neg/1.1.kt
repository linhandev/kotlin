// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, documentation-comments -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: DocComment unclosed documentation comment
 */

// TESTCASE NUMBER: 1
package syntax.documentationcomments.p1.neg1

/**
 * missing closing marker
fun case1(): Int = 1
<!SYNTAX!><!>