// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, type-system, introduction-1 -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 3 -> sentence 3
 *                type-system, type-kinds, type-parameters -> paragraph 3 -> sentence 3
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: non-reified type parameter cannot be used in an is-check against a Java platform-typed value
 */

// TESTCASE NUMBER: 1
fun <T> case_1(): Boolean = System.getProperty("user.name") is <!CANNOT_CHECK_FOR_ERASED!>T<!>
