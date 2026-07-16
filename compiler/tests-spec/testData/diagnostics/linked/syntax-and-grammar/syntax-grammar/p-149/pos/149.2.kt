// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 149 -> sentence 149
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 148 -> sentence 148
 * NUMBER: 2
 * DESCRIPTION: excl EXCL_WS token in not-null assertion
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p149.pos2

fun case1() {
    val s: String? = "a"
    val x = s!!
}
