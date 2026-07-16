// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 10 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: topLevelObject invalid classDeclaration with hard keyword return as classParameter
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p10.neg2

class TopLevel10(<!SYNTAX!>return<!>: Int)
