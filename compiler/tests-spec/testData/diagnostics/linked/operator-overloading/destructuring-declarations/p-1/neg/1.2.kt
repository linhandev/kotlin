// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, destructuring-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: extension component function without operator modifier reports OPERATOR_MODIFIER_REQUIRED
 */

// TESTCASE NUMBER: 1
class Dummy9112

fun Dummy9112.component1() = "1"
fun Dummy9112.component2() = "2"

fun case_1() {
    val (<!OPERATOR_MODIFIER_REQUIRED!>a<!>, <!OPERATOR_MODIFIER_REQUIRED!>b<!>) = Dummy9112()
}
