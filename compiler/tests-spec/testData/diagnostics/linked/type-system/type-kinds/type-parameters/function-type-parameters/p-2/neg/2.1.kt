// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, function-type-parameters -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Function type parameters do not support mixed-site variance
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <<!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>out<!> T> case_1() {}


// TESTCASE NUMBER: 2
fun <<!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>in<!> T> case_2() {}


// TESTCASE NUMBER: 3
fun <<!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>out<!> T, <!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>in<!> X> case_3() {}


// TESTCASE NUMBER: 4
class Case4 {
    fun <<!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>out<!> T> bar() {}
}


// TESTCASE NUMBER: 5
fun <<!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>in<!> T> T.case_5() {}
