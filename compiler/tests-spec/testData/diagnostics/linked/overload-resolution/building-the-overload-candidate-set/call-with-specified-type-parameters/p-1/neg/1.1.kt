// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, call-with-specified-type-parameters -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: type argument list with wrong arity is rejected during OCS filtering
 */

fun <T> foo11208N(): String = "one"

// TESTCASE NUMBER: 1
fun case_1(): String = foo11208N<!WRONG_NUMBER_OF_TYPE_ARGUMENTS!><Int, String><!>()
