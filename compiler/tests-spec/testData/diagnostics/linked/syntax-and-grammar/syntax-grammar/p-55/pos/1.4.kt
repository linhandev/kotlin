// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 55 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: userType NL before dot between simpleUserType
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p55.pos4

class Outer { class Inner }
val value: Outer.
Inner = Outer.Inner()
