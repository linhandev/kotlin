// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, function-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Function type assignments must respect parameter contravariance and return covariance
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun foo(i: Number): Number = i

fun case_1() {
    val fooRef: (Number) -> Number = ::foo
    val ref: (String) -> Number = <!TYPE_MISMATCH!>fooRef<!>
}


// TESTCASE NUMBER: 2
fun bar(): String = ""

fun case_2() {
    val barRef: () -> String = ::bar
    val f: (Int) -> Int = <!TYPE_MISMATCH!>barRef<!>
}


// TESTCASE NUMBER: 3
fun baz(x: Int): String = x.toString()

fun case_3(f: (String) -> String) {}

fun case_3_use() {
    case_3(<!TYPE_MISMATCH!>::baz<!>)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val f: (Int) -> String = { x -> x.toString() }
    val g: (Int) -> Int = <!TYPE_MISMATCH!>f<!>
}


// TESTCASE NUMBER: 5
fun case_5() {
    val f: (Int) -> String = ::baz
    val g: (String) -> String = <!TYPE_MISMATCH!>f<!>
}
