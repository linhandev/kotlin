// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 33 -> sentence 33
 *                type-system, introduction-1 -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: non-reified type parameter T::class is not allowed, T is not a runtime-available type
 */

// TESTCASE NUMBER: 1
fun <T> case_1(): kotlin.reflect.KClass<<!UPPER_BOUND_VIOLATED!>T<!>> = <!TYPE_PARAMETER_AS_REIFIED!>T::class<!>
