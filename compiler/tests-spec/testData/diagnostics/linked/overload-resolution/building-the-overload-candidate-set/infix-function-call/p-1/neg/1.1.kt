// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, infix-function-call -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: infix call syntax excludes callables without infix modifier from OCS
 */

class A11203N
class B11203N

fun A11203N.join11203N(b: B11203N) {}

// TESTCASE NUMBER: 1
fun case_1() {
    val a = A11203N()
    val b = B11203N()
    a <!INFIX_MODIFIER_REQUIRED!>join11203N<!> b
}
