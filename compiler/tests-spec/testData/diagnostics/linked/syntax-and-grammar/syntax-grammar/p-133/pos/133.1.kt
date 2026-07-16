// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 133 -> sentence 133
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 143 -> sentence 143
 * syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 51
 * NUMBER: 1
 * DESCRIPTION: typeTest when is type condition
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p133.pos1

fun case1() {
    val x: Any = 1
    when (x) { is Int -> Unit; else -> Unit }
}
