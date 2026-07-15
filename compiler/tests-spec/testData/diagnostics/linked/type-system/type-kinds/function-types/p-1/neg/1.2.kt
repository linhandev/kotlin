// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, function-types -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Function types reject wrong arity, receiver mismatch, and incompatible return types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val f: (Int, String) -> Boolean = { a, b -> a.toString() == b }
    val g: (Int) -> Boolean = <!TYPE_MISMATCH!>f<!>
}


// TESTCASE NUMBER: 2
fun Int.ext(): String = toString()

fun case_2() {
    val ext: Int.() -> String = Int::ext
    val wrong: (String) -> String = <!TYPE_MISMATCH!>ext<!>
}


// TESTCASE NUMBER: 3
fun case_3(): Int = 1

fun case_3_use() {
    val f: () -> Int = ::case_3
    val wrong: (Int) -> String = <!TYPE_MISMATCH!>f<!>
}


// TESTCASE NUMBER: 4
fun case_4(x: (Int) -> String) {}

fun case_4_use() {
    val f: (Int, Int) -> String = { a, b -> "$a$b" }
    case_4(<!TYPE_MISMATCH!>f<!>)
}


// TESTCASE NUMBER: 5
fun case_5() {
    val ho: ((Int) -> String) -> Unit = {}
    val f: (Int) -> String = <!TYPE_MISMATCH!>ho<!>
}
