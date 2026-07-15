// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, kotlin.function -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Unit-returning lambda is not a subtype of kotlin.Function(String)
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val f: Function<String> = <!TYPE_MISMATCH!>{}<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val f: Function<Int> = <!TYPE_MISMATCH!>{ x: Int -> x.toString() }<!>
}


// TESTCASE NUMBER: 3
fun case_3() {
    val f: Function<String> = <!TYPE_MISMATCH!>{ x: Int, y: String -> x.toString() == y }<!>
}


// TESTCASE NUMBER: 4
fun case_4(): Int = 42

fun case_4_use() {
    val f: Function<String> = <!TYPE_MISMATCH!>{ -> case_4() }<!>
}


// TESTCASE NUMBER: 5
fun Int.case_5(): String = toString()

fun case_5_use() {
    val ext: Int.() -> String = Int::case_5
    val f: Function<Unit> = <!TYPE_MISMATCH!>ext<!>
}
