// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 75 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: assignment NL before expression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p75.pos3

fun case1() {
    var x: Int
    x = 1
    x =
    2
    if (x != 2) error("assignment failed")
}
