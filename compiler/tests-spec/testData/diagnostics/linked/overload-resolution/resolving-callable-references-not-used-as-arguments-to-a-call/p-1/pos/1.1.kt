// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, resolving-callable-references-not-used-as-arguments-to-a-call -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: expected type (Int) -> Int selects Int overload for standalone callable reference
 */

fun foo1161(i: Int): Int = i
fun foo1161(d: Double): Double = d

// TESTCASE NUMBER: 1
fun case_1() {
    val ref: (Int) -> Int = ::foo1161
    val ok = ref(2) == 2
}
