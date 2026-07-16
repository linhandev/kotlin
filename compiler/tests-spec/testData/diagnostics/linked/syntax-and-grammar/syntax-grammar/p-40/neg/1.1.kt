// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 40 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: setter invalid simpleIdentifier in set parentheses
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p40.neg1

var value: Int = 1
    set(<!SYNTAX!>return<!>) { field = 1 }
