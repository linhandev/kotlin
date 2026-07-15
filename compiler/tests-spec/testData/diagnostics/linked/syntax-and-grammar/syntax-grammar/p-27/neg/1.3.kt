// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 27 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: classMemberDeclarations extra token after member
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p27.neg3

class Case1 {
    val value: Int = 1
    <!SYNTAX!>extra<!>
}
