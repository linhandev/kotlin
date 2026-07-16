// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 46 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: secondaryConstructor invalid simpleIdentifier in parameter
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p46.neg1

class Case1(val value: Int = 0) {
    constructor(<!SYNTAX!>return<!>: String) : this(0) {}
}
