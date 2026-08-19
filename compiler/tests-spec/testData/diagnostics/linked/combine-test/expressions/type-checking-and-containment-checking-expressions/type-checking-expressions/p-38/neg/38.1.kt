// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: type-system, type-kinds, type-parameters -> paragraph 38 -> sentence 38
 *                type-system, type-kinds, parameterized-classifier-types -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: non-reified type parameter T used in parameterized type is-check reports CANNOT_CHECK_FOR_ERASED
 */

// TESTCASE NUMBER: 1
fun <T> case_1(value: Any?): Boolean = value is <!CANNOT_CHECK_FOR_ERASED!>List<T><!>
