// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, type-parameters, function-type-parameters -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Function type parameters with out variance modifier are ill-formed
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
val <<!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>out<!> T> T.case_1: Int
    get() = 1


// TESTCASE NUMBER: 2
val <<!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>in<!> T> T.case_2: Int
    get() = 1


// TESTCASE NUMBER: 3
class Case3 {
    fun <<!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>out<!> <!REPEATED_MODIFIER!>out<!> T> bar() {}
}


// TESTCASE NUMBER: 4
interface Case4 {
    fun <<!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>in<!> T> f(x: T)
}


// TESTCASE NUMBER: 5
object Case5 {
    fun <<!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>out<!> T, <!VARIANCE_ON_TYPE_PARAMETER_NOT_ALLOWED!>in<!> R> pair(a: T, b: R): Pair<T, R> = a to b
}
