// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 39 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: getter invalid parameter in get parentheses
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p39.neg2

val value: Int
    get(<!SYNTAX!>1<!>) = 1
