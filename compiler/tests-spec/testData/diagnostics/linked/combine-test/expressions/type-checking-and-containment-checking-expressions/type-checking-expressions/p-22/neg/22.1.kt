// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: type-system, type-kinds, type-parameters -> paragraph 22 -> sentence 22
 *                runtime-type-information, runtime-available-types -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: non-reified generic type parameter T is not a runtime-available type for !is-check
 */

// TESTCASE NUMBER: 1
fun <T> case_1(value: Any?): Boolean = value !is <!CANNOT_CHECK_FOR_ERASED!>T<!>
