// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: value class with Int and String underlying types compiles successfully
 */

// TESTCASE NUMBER: 1
@JvmInline
value class IntWrapper(val value: Int)

// TESTCASE NUMBER: 2
@JvmInline
value class StringWrapper(val value: String)
