// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 166 -> sentence 166
 * NUMBER: 1
 * DESCRIPTION: parameterModifier crossinline without inline function
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p166.neg1

fun case1(<!ILLEGAL_INLINE_PARAMETER_MODIFIER!>crossinline<!> f: () -> Unit) {}
