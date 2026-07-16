// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 50 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: enumEntry numeric simpleIdentifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p50.neg2

enum class Case1(val value: Int) {<!SYNTAX!><!> <!SYNTAX!>1<!><!SYNTAX!>(<!>value<!SYNTAX!>)<!> }
