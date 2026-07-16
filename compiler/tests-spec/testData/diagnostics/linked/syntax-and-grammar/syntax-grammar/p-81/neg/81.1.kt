// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 81 -> sentence 81
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 82 -> sentence 82
 * syntax-and-grammar, syntax-grammar -> paragraph 140 -> sentence 140
 * NUMBER: 1
 * DESCRIPTION: equality trailing equals operator missing right comparison
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p81.neg1

interface <!SYNTAX!>2<!>
