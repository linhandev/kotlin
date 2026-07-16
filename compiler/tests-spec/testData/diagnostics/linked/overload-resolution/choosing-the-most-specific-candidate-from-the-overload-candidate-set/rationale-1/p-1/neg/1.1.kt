// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, choosing-the-most-specific-candidate-from-the-overload-candidate-set, rationale-1 -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: equally specific candidates report overload resolution ambiguity
 */

fun tie11401N(y: Int, x: Short): Unit {}

fun tie11401N(y: Int, x: Long): Unit {}

// TESTCASE NUMBER: 1
fun case_1(): Unit = <!OVERLOAD_RESOLUTION_AMBIGUITY!>tie11401N<!>(1, 1)
