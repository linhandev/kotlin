// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 146 -> sentence 146
 * NUMBER: 1
 * DESCRIPTION: asOperator as cast in expression
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p146.pos1

fun case1() { val x: Any = 1; x as Int }
