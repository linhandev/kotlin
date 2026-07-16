// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 76 -> sentence 76
 * NUMBER: 2
 * DESCRIPTION: semi newline terminates statement
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p76.pos2

fun nlSemi() {
    val a = 1
    val b = 2
}
