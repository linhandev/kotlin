// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -TYPE_MISMATCH_IN_RANGE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: when expression branch with in operator fails when receiver has no suitable contains operator
 */

// TESTCASE NUMBER: 1
class Box

fun test(x: Int): String = when (x) {
    <!UNRESOLVED_REFERENCE_WRONG_RECEIVER!>in<!> Box() -> "inside"
    else -> "other"
}
