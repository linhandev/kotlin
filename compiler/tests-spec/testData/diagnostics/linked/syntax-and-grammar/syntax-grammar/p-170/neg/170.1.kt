// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 170 -> sentence 170
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 172 -> sentence 172
 * NUMBER: 1
 * DESCRIPTION: singleAnnotation unclosed annotation paren
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p170.neg1

fun case1() { @Deprecated<!NO_VALUE_FOR_PARAMETER!>()<!> fun f() {} }
