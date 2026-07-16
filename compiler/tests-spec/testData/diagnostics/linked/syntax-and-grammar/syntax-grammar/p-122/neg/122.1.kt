// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNUSED_ANONYMOUS_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 122 -> sentence 122
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 41 -> sentence 41
 * syntax-and-grammar, syntax-grammar -> paragraph 34 -> sentence 34
 * NUMBER: 1
 * DESCRIPTION: anonymousFunction missing function body
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p122.neg1

fun case1() { val f = fun(x: Int): Int =<!SYNTAX!><!> }
