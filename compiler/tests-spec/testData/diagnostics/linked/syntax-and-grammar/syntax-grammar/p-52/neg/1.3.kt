// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 52 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: typeReference invalid misspelled dynamic keyword
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p52.neg3

val value: <!UNRESOLVED_REFERENCE!>dynamc<!> = Any()
