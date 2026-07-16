// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 95 -> sentence 95
 * PRIMARY LINKS: syntax-and-grammar, syntax-grammar -> paragraph 105 -> sentence 105
 * syntax-and-grammar, syntax-grammar -> paragraph 148 -> sentence 148
 * syntax-and-grammar, syntax-grammar -> paragraph 103 -> sentence 103
 * syntax-and-grammar, syntax-grammar -> paragraph 101 -> sentence 101
 * syntax-and-grammar, syntax-grammar -> paragraph 102 -> sentence 102
 * NUMBER: 1
 * DESCRIPTION: postfixUnarySuffix incomplete type arguments list
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p95.neg1

fun <T> id(v: T): T = v

fun case1() { <!FUNCTION_CALL_EXPECTED, NEW_INFERENCE_NO_INFORMATION_FOR_PARAMETER, NO_VALUE_FOR_PARAMETER!>id<!><!DEBUG_INFO_MISSING_UNRESOLVED!><<!><!SYNTAX!><!> }
