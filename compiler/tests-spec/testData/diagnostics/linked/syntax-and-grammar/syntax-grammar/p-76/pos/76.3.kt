// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 76 -> sentence 76
 * NUMBER: 3
 * DESCRIPTION: semi semicolon followed by optional newlines
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p76.pos3

fun case1() {
    val x = 1;


    val y = 2
}
