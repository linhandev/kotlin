// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 22 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: explicitDelegation missing expression after by
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p22.neg3

interface Base
class Case1 : Base by (<!SYNTAX!><!>
