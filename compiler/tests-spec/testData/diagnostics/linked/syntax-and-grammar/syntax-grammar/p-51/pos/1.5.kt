// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, syntax-grammar -> paragraph 51 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: type definitelyNonNullableType branch
 */

// TESTCASE NUMBER: 1

package syntax.grammar.p51.pos5

fun <T> case1(x: T & Any): T & Any = x
