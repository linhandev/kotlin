// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, documentation-comments -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: DocComment simple documentation comment before declaration
 */
// TESTCASE NUMBER: 1

package syntax.documentationcomments.p1.pos1

/** Returns one. */
fun case1(): Int = 1
