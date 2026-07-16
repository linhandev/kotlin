// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, checking-constraint-system-soundness -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: satisfiable constraint system allows type argument inference
 */

fun <T> id1321(t: T): T = t

// TESTCASE NUMBER: 1
fun box(): String = if (id1321(42) == 42) "OK" else "NOK"
