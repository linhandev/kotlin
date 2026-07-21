/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, finding-optimal-constraint-system-solution -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: pull-up constraint picks LUB of lower bounds for free inference variable
 */

fun <T> id1322(t: T): T = t

// TESTCASE NUMBER: 1
fun box(): String = if (id1322(42) == 42) "OK" else "NOK"
