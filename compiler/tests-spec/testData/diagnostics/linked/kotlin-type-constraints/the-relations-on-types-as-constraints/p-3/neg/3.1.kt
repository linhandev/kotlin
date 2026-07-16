// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: kotlin-type-constraints, the-relations-on-types-as-constraints -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: imprecise LUB upper bound is not assignable to unrelated narrower type Number
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val flag = true
    val e = if (flag) 1 else "x"
    val n: Number = <!TYPE_MISMATCH!>e<!>
}
