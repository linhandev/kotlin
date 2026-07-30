// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: runtime-type-information, runtime-available-types -> paragraph 24 -> sentence 24
 *                type-system, type-kinds, parameterized-classifier-types -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: parameterized type Map<String, Int> is not a runtime-available type for !is-check
 */

// TESTCASE NUMBER: 1
fun case_1(value: Any?): Boolean = value !is <!CANNOT_CHECK_FOR_ERASED!>Map<String, Int><!>
