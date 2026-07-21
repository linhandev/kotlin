/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, type-constraint-definition -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: type constraint T <: U holds for generic upper bound with satisfying argument type
 */

fun <T : Number> pick131(t: T): T = t

// TESTCASE NUMBER: 1
fun box(): String = if (pick131(42) == 42) "OK" else "NOK"
