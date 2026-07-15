// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, kotlin.any -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Nullable enum, nested, and inherited user-defined types are not subtypes of non-nullable kotlin.Any
 * HELPERS: checkType, functions
 */

// TESTCASE NUMBER: 1
enum class Case1 {TEST;
    inner class Case1(val x: Int = 0)
}

fun case_1(x: Case1.Case1?) {
    checkSubtype<Any>(<!TYPE_MISMATCH!>x<!>)
    funWithAnyArg(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 2
enum class Case2 {;
    data class Case2(val x: Int)
}

fun case_2(x: Case2.Case2?) {
    checkSubtype<Any>(<!TYPE_MISMATCH!>x<!>)
    val z: Any = <!TYPE_MISMATCH!>x<!>
    funWithAnyArg(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 3
enum class Case3 {;
    data class Case3(val x: Int)
}

typealias Case3_1 = Case3.Case3

fun case_3(x: Case3_1?) {
    checkSubtype<Any>(<!TYPE_MISMATCH!>x<!>)
    val z: Any = <!TYPE_MISMATCH!>x<!>
    funWithAnyArg(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 4
interface Case4 {;
    data class Case4(val x: Int) {
        interface Case4 {
            fun marker(): Unit = Unit
        }
    }
}

fun case_4(x: Case4.Case4.Case4?) {
    checkSubtype<Any>(<!TYPE_MISMATCH!>x<!>)
    funWithAnyArg(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 5
class Case5 : Any()

fun case_5(x: Case5?) {
    checkSubtype<Any>(<!TYPE_MISMATCH!>x<!>)
    val z: Any = <!TYPE_MISMATCH!>x<!>
    funWithAnyArg(<!TYPE_MISMATCH!>x<!>)
}
