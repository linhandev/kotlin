// FIR_IDENTICAL
// LANGUAGE: -GenericInlineClassParameter
// DIAGNOSTICS: -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, value-class-declaration -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: value class generic underlying type requires Kotlin 1.8+
 */

// TESTCASE NUMBER: 1
@JvmInline
value class GenericWrapper<T>(val value: <!UNSUPPORTED_FEATURE!>T<!>)
