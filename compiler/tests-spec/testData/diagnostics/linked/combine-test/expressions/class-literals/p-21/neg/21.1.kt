// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 21 -> sentence 21
 *                declarations, declarations-with-type-parameters -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: non-reified type parameter T::class is not allowed, verifying compile-time failure
 */

// TESTCASE NUMBER: 1
fun <T> case1(): kotlin.reflect.KClass<<!UPPER_BOUND_VIOLATED!>T<!>> = <!TYPE_PARAMETER_AS_REIFIED!>T::class<!>
