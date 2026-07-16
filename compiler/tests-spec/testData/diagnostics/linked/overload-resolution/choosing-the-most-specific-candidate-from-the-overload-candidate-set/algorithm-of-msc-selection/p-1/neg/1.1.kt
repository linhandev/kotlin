// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, choosing-the-most-specific-candidate-from-the-overload-candidate-set, algorithm-of-msc-selection -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: overload ambiguity when MSC additional steps leave equally applicable candidates
 */

fun tie11402N(x: Int, y: Number?): Unit {}

fun tie11402N(vararg x: Short): Unit {}

// TESTCASE NUMBER: 1
fun case_1(): Unit = <!OVERLOAD_RESOLUTION_AMBIGUITY!>tie11402N<!>(1, 1)
