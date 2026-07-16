// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNUSED_CHANGED_VALUE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 148 -> sentence 148
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 149 -> sentence 149
 * NUMBER: 2
 * DESCRIPTION: postfixUnaryOperator decrement and not-null assertion operators
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p148.pos2

fun case1() {
    var i = 1
    i--
    val s: String? = "a"
    val x = s!!
}
