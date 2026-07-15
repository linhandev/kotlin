// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -UNREACHABLE_CODE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, kotlin.nothing -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Int literals and nullable String are not subtypes of kotlin.Nothing
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class CustomClass
fun ok(): String = "ok"

fun case_1() {
    val a: Nothing = <!TYPE_MISMATCH!>"test"<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val b: Nothing = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val c: Nothing = <!TYPE_MISMATCH!>1.toShort()<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val d: Nothing = <!TYPE_MISMATCH!>1.toByte()<!>
    val e: Nothing = <!CONSTANT_EXPECTED_TYPE_MISMATCH!>1L<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    checkSubtype<Nothing>(<!TYPE_MISMATCH!>arrayOf<Any>()<!>)
    checkSubtype<Nothing>(<!TYPE_MISMATCH!>CustomClass()<!>)
    checkSubtype<Nothing>(<!TYPE_MISMATCH!>Any()<!>)
    val f: Nothing = <!TYPE_MISMATCH!>{ _: Int -> "" }<!>
}


// TESTCASE NUMBER: 6
fun case_6() {
    val x: Nothing = <!TYPE_MISMATCH!>ok()<!>
}


// TESTCASE NUMBER: 7
fun case_7() {
    val n: Int? = 1
    val x: Nothing = <!TYPE_MISMATCH!>n<!>
}