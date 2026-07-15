// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNUSED_CHANGED_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 147 -> sentence 147
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 149 -> sentence 149
 * NUMBER: 2
 * DESCRIPTION: prefixUnaryOperator increment decrement plus and excl tokens
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p147.pos2

fun case1() {
    var i = 1
    ++i
    --i
    val p = +2
    val q = !false
}
