// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 104 -> sentence 104
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 68 -> sentence 68
 * syntax-and-grammar, syntax-grammar -> paragraph 169 -> sentence 169
 * syntax-and-grammar, syntax-grammar -> paragraph 119 -> sentence 119
 * NUMBER: 2
 * DESCRIPTION: annotatedLambda label prefix on lambda literal
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p104.pos2

fun case1() { val f = @Suppress("UNUSED") loop@ { 1 } }
