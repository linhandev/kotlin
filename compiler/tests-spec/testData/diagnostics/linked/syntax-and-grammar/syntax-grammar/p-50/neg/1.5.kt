// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 50 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: enumEntry missing closing brace in classBody
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p50.neg5

enum class Case1 { A { fun local(): Int = 1<!SYNTAX!><!>
