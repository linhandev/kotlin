// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 163 -> sentence 163
 * NUMBER: 1
 * DESCRIPTION: functionModifier duplicate tailrec modifier
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p163.neg1

<!NO_TAIL_CALLS_FOUND!>tailrec<!> <!REPEATED_MODIFIER!>tailrec<!> fun case1(): Int = 0
