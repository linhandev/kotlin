// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 65 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: definitelyNonNullableType missing ampersand
 */

// TESTCASE NUMBER: 1
package syntax.grammar.p65.neg1

fun <T> case1(x: T<!SYNTAX!><!> <!VALUE_PARAMETER_WITH_NO_TYPE_ANNOTATION!>Any<!>): T & Any = <!TYPE_MISMATCH!>x<!>
