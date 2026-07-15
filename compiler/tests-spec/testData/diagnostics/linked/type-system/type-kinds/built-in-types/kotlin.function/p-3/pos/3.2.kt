// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, kotlin.function -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 1 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: Higher-order, anonymous, and value-parameter function types are subtypes of kotlin.Function(R)
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    val ext: Int.() -> Unit = { println(this) }
    checkSubtype<Function<Unit>>(ext)
    val f: Function<Unit> = ext
}


// TESTCASE NUMBER: 2
fun case_2() {
    checkSubtype<Function<String>>(fun(x: Int): String { return x.toString() })
    val f: Function<String> = fun(x: Int): String { return x.toString() }
}


// TESTCASE NUMBER: 3
fun case_3(x: Int): Int = x

fun case_3_use() {
    checkSubtype<Function<Int>>(::case_3)
    val f: Function<Int> = ::case_3
}


// TESTCASE NUMBER: 4
fun case_4() {
    val higherOrder: ((Int) -> String) -> Unit = { f -> f(1) }
    checkSubtype<Function<Unit>>(higherOrder)
    val f: Function<Unit> = higherOrder
}


// TESTCASE NUMBER: 5
fun case_5(x: (Int) -> String) {
    checkSubtype<Function<String>>(x)
    val f: Function<String> = x
}
