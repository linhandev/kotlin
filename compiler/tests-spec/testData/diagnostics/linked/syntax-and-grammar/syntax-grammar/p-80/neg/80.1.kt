// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 80 -> sentence 80
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 81 -> sentence 81
 * NUMBER: 1
 * DESCRIPTION: conjunction trailing and operator missing right equality
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p80.neg1

fun case1() { val x = true &&<!SYNTAX!><!> }
