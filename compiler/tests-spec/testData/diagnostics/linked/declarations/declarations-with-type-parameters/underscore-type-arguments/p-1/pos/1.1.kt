// FIR_IDENTICAL
// LANGUAGE: +PartiallySpecifiedTypeArguments
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNCHECKED_CAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, declarations-with-type-parameters, underscore-type-arguments -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: underscore type arguments allow partial explicit specification with inference
 */

// TESTCASE NUMBER: 1
fun <T> foo(t: T): T = t

fun <T, R : List<T>> foo(t: T, d: Double = 42.0): T = t

fun useUnderscore(): Double = foo<_, _>(42.0)
