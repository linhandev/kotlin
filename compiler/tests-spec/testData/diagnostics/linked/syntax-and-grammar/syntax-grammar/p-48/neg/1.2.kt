// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 48 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: enumClassBody missing semicolon before classMemberDeclarations with enumEntries
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p48.neg2

enum class Case1 { A, B<!SYNTAX!><!> fun value(): Int = 1 }
