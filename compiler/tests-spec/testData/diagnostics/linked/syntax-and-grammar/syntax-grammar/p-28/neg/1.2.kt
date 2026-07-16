// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 28 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: classMemberDeclaration incomplete secondaryConstructor
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p28.neg2

class Case1 {
    constructor(<!SYNTAX!>return<!>: String)
}
