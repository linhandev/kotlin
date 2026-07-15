// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -CANNOT_INFER_PARAMETER_TYPE -UNUSED_ANONYMOUS_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 121 -> sentence 121
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 35 -> sentence 35
 * syntax-and-grammar, syntax-grammar -> paragraph 120 -> sentence 120
 * NUMBER: 1
 * DESCRIPTION: lambdaParameter missing lambda body after param
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p121.neg1

fun case1() { val f = { n: Int -> }<!SYNTAX!><!>
