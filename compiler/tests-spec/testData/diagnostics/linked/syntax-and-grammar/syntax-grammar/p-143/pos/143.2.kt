// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 143 -> sentence 143
 * NUMBER: 2
 * DESCRIPTION: isOperator not is keyword in expression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p143.pos2

fun case1() {
    val x: Any = 1
    val b = x !is Int
}
