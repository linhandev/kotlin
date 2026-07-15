// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, kotlin.function -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: Function reference with mismatched return type is not a subtype of kotlin.Function(R)
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val ext: Int.() -> Unit = { println(this) }
    val f: Function<String> = <!TYPE_MISMATCH!>ext<!>
}


// TESTCASE NUMBER: 2
fun case_2() {
    val f: Function<Int> = <!TYPE_MISMATCH!>fun(x: Int): String { return x.toString() }<!>
}


// TESTCASE NUMBER: 3
fun case_3(x: Int): Int = x

fun case_3_use() {
    val f: Function<String> = <!TYPE_MISMATCH!>{ -> case_3(1) }<!>
}


// TESTCASE NUMBER: 4
fun case_4() {
    val higherOrder: ((Int) -> String) -> Unit = { f -> f(1) }
    val f: Function<String> = <!TYPE_MISMATCH!>higherOrder<!>
}


// TESTCASE NUMBER: 5
fun case_5(x: (Int) -> String) {
    val f: Function<Int> = <!TYPE_MISMATCH!>x<!>
}
