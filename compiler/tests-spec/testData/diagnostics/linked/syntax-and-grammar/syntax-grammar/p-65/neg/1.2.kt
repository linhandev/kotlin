// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 65 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: definitelyNonNullableType invalid empty left side
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p65.neg2

fun <T> case1(x: (<!SYNTAX!><!>) & Any) {}
