// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 10 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: topLevelObject multiple declarations with semis
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p10.pos5

val case1: Int = 1;
fun case2(): Int = 2;
class Case3
