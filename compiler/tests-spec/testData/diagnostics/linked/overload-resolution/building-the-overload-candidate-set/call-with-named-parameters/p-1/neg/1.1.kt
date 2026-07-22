// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: named argument with no matching formal parameter name is rejected during OCS filtering
 */

fun pick11206N(a: Int = 0) {}

// TESTCASE NUMBER: 1
fun case_1() {
    pick11206N(<!NAMED_PARAMETER_NOT_FOUND!>x<!> = 1)
}
