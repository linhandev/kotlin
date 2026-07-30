// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 8 -> sentence 8
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: KClass<String> cannot be assigned to KClass<String?> due to upper bound Any constraint, verifying compile-time failure
 */

val a: kotlin.reflect.KClass<String> = String::class

// TESTCASE NUMBER: 1
fun case1(): kotlin.reflect.KClass<<!UPPER_BOUND_VIOLATED!>String?<!>> = <!TYPE_MISMATCH!>a<!>
