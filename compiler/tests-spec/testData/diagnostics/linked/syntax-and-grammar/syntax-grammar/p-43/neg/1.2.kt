// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 43 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: parameterWithOptionalType numeric simpleIdentifier
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p43.neg2

var count: Int = 2
    set(<!SYNTAX!>2<!>: Int) { field = 2 }
