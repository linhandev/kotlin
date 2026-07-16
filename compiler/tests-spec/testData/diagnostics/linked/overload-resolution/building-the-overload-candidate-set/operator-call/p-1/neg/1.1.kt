// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, operator-call -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: operator syntax excludes callables without operator modifier from OCS
 */

class A11204N
class B11204N

fun A11204N.plus(b: B11204N): A11204N = this

// TESTCASE NUMBER: 1
fun case_1() {
    val a = A11204N()
    val b = B11204N()
    a <!OPERATOR_MODIFIER_REQUIRED!>+<!> b
}
