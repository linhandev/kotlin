// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 72 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: forStatement with annotation before variableDeclaration
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p72.pos2

fun case1() { @Suppress("UNUSED") for (i in 1..2) { } }
