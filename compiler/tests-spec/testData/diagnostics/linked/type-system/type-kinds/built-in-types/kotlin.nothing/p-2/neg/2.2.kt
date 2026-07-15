// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, kotlin.nothing -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Custom classes and function types are not subtypes of kotlin.Nothing
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class CustomClass

fun case_1() {
    checkSubtype<Nothing>(<!TYPE_MISMATCH!>mutableListOf<Nothing>()<!>)
}


// TESTCASE NUMBER: 2
fun case_2() {
    checkSubtype<Nothing>(<!TYPE_MISMATCH!>mutableListOf<String>()<!>)
}


// TESTCASE NUMBER: 3
fun case_3() {
    checkSubtype<Nothing>(<!TYPE_MISMATCH!>mutableListOf<CustomClass>()<!>)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val d: Nothing = <!TYPE_MISMATCH!>"test"<!>
    val e: Nothing = <!TYPE_MISMATCH!>CustomClass()<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    checkSubtype<Nothing>(<!TYPE_MISMATCH!>mutableListOf<Any?>()<!>)
}
