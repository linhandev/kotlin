// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: value class structural equality with == operator
 */

// TESTCASE NUMBER: 1
@JvmInline
value class Token(val value: Int)

fun test(a: Token, b: Token): Boolean = a == b
