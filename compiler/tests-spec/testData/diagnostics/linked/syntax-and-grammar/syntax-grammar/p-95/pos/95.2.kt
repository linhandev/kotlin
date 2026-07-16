// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 95 -> sentence 95
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 105 -> sentence 105
 * syntax-and-grammar, syntax-grammar -> paragraph 148 -> sentence 148
 * syntax-and-grammar, syntax-grammar -> paragraph 103 -> sentence 103
 * syntax-and-grammar, syntax-grammar -> paragraph 101 -> sentence 101
 * syntax-and-grammar, syntax-grammar -> paragraph 102 -> sentence 102
 * NUMBER: 2
 * DESCRIPTION: postfixUnarySuffix type arguments call suffix
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p95.pos2

fun <T> id(v: T): T = v

fun case1() { id<Int>(1) }
