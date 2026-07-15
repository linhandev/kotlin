// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 164 -> sentence 164
 * NUMBER: 1
 * DESCRIPTION: propertyModifier const on non literal
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p164.neg1

const val CASE1: Int = <!CONST_VAL_WITH_NON_CONST_INITIALIZER!>run { 1 }<!>
