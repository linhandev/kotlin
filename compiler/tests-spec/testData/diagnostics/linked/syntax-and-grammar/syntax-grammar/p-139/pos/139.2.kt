// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 139 -> sentence 139
 * NUMBER: 2
 * DESCRIPTION: assignmentAndOperator minus multiply divide remainder assign operators
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p139.pos2

fun case1() {
    var x = 10
    x -= 1
    x *= 2
    x /= 2
    x %= 3
}
