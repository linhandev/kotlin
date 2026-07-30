// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: runtime-type-information, runtime-available-types -> paragraph 23 -> sentence 23
 *                type-system, type-kinds, parameterized-classifier-types -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: parameterized type List<String> is not a runtime-available type for is-check
 */

// TESTCASE NUMBER: 1
fun case_1(value: Any?): Boolean = value is <!CANNOT_CHECK_FOR_ERASED!>List<String><!>
