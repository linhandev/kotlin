// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 25 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: typeConstraints NL after where
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p25.pos4

class Case1<T> where
    T : Any, T : Comparable<T>
