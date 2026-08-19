// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 39 -> sentence 39
 * PRIMARY LINKS: declarations, declarations-with-type-parameters, reified-type-parameters -> paragraph 39 -> sentence 39
 *                type-system, type-kinds, type-parameters -> paragraph 39 -> sentence 39
 * NUMBER: 1
 * DESCRIPTION: mixed reified T and non-reified R in Pair<T, R> is-check — whole parameterized type is not runtime-available, reports CANNOT_CHECK_FOR_ERASED
 */

// TESTCASE NUMBER: 1
inline fun <reified T, R> case_1(value: Any?): Boolean = value is <!CANNOT_CHECK_FOR_ERASED!>Pair<T, R><!>
