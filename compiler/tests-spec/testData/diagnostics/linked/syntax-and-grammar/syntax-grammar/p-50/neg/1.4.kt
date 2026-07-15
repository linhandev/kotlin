// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 50 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: enumEntry missing opening parenthesis in valueArguments
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p50.neg4

enum class Case1 { A<!SYNTAX!><!> <!SYNTAX!>1<!><!SYNTAX!>)<!> }
