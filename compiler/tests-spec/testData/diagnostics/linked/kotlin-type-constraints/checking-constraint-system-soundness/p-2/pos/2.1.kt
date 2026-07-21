// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, checking-constraint-system-soundness -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: reduction eliminates resolved constraint Int <: Number
 * HELPERS: checkType
 */

fun widen1321(n: Number): Number = n

// TESTCASE NUMBER: 1
fun case_1() {
    val result: Number = widen1321(42)
    checkSubtype<Number>(result)
}
