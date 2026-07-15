// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 10 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: topLevelObject invalid keyword declaration return
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p10.neg1

<!SYNTAX!>return<!>
private object TopLevelReturnAnchor { const val CASE = 1 }
