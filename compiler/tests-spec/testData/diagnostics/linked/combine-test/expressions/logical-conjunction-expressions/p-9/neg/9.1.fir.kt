// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, logical-conjunction-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 9 -> sentence 9
 *                type-system, introduction-1 -> paragraph 9 -> sentence 9
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: Boolean? cannot be used directly in &&
 */

// TESTCASE NUMBER: 1
fun test(a: Boolean?, b: Boolean): Boolean = <!CONDITION_TYPE_MISMATCH!>a<!> && b
