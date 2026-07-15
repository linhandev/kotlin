// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, kotlin.any -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Nullable user-defined types are not subtypes of non-nullable kotlin.Any
 * HELPERS: checkType, functions
 */

// TESTCASE NUMBER: 1
class Case1

fun case_1(x: Case1?) {
    checkSubtype<Any>(<!TYPE_MISMATCH!>x<!>)
    val z: Any = <!TYPE_MISMATCH!>x<!>
    funWithAnyArg(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 2
interface Case2

fun case_2(x: Case2?) {
    checkSubtype<Any>(<!TYPE_MISMATCH!>x<!>)
    fun z(): Any = <!TYPE_MISMATCH!>x<!>
    funWithAnyArg(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 3
data class Case3(val x: Int)

fun case_3(x: Case3?) {
    checkSubtype<Any>(<!TYPE_MISMATCH!>x<!>)
    funWithAnyArg(<!TYPE_MISMATCH!>x<!>)
    val z: Any = <!TYPE_MISMATCH!>x<!>
}


// TESTCASE NUMBER: 4
sealed class Case4

fun case_4(x: Case4?) {
    checkSubtype<Any>(<!TYPE_MISMATCH!>x<!>)
    funWithAnyArg(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 5
annotation class Case5 {
    class Case5 {}
}

fun case_5(x: Case5?, y: Case5.Case5?) {
    checkSubtype<Any>(<!TYPE_MISMATCH!>x<!>)
    checkSubtype<Any>(<!TYPE_MISMATCH!>y<!>)
    val z1: Any = <!TYPE_MISMATCH!>x<!>
    val z2: Any = <!TYPE_MISMATCH!>y<!>
    funWithAnyArg(<!TYPE_MISMATCH!>x<!>)
    funWithAnyArg(<!TYPE_MISMATCH!>y<!>)
}
