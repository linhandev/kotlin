// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 83 -> sentence 83
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 84 -> sentence 84
 * syntax-and-grammar, syntax-grammar -> paragraph 103 -> sentence 103
 * syntax-and-grammar, syntax-grammar -> paragraph 105 -> sentence 105
 * NUMBER: 1
 * DESCRIPTION: genericCallLikeComparison unclosed type argument list
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p83.neg1

fun <T> id(v: T): T = v

fun case1() { <!FUNCTION_CALL_EXPECTED, NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER, NO_VALUE_FOR_PARAMETER!>id<!><!DEBUG_INFO_MISSING_UNRESOLVED!><<!>(1) }
