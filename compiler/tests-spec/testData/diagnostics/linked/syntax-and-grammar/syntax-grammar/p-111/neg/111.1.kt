// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 111 -> sentence 111
 * NUMBER: 1
 * DESCRIPTION: literalConstant invalid numeric literal
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p111.neg1

fun case1() { val x = 1.2<!SYNTAX, UNSUPPORTED!>.3<!> }
