// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, type-constraint-definition -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: constraint List<T> <: List<U> satisfied when T is inferred as Int
 */

fun <T> singleton131(t: T): List<T> = listOf(t)

// TESTCASE NUMBER: 1
fun box(): String = if (singleton131(42) == listOf(42)) "OK" else "NOK"
