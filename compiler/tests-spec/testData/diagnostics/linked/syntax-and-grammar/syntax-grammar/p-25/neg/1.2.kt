// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 25 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: typeConstraints invalid typeParameter in second constraint
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p25.neg3

class Case1<T, U> where T : Any, U <!SYNTAX!>return<!> CharSequence
