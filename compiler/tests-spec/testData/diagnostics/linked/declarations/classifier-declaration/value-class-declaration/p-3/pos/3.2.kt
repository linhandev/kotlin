// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: value class may use Boolean and Char as underlying types
 */

// TESTCASE NUMBER: 1
@JvmInline
value class Flag(val enabled: Boolean)

// TESTCASE NUMBER: 2
@JvmInline
value class Code(val ch: Char)
