// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 28 -> sentence 28
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: String::class of type KClass<String> cannot be assigned to KClass<Int>, verifying compile-time failure
 */

// TESTCASE NUMBER: 1
fun case1(): kotlin.reflect.KClass<Int> = <!TYPE_MISMATCH!>String::class<!>
