// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, function-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, built-in-types, kotlin.function -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Function type variance allows contravariant parameters and covariant return types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun foo(i: Number): Number = i

fun case_1() {
    val fooRef: (Int) -> Any = ::foo
    checkSubtype<(Int) -> Any>(::foo)
}


// TESTCASE NUMBER: 2
fun Number.bar(): Number = this

fun case_2() {
    val barRef: (Int) -> Any = Number::bar
    checkSubtype<(Int) -> Any>(Number::bar)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val f: (Int, String) -> Boolean = { x, y -> x.toString() == y }
    checkSubtype<(Int, String) -> Boolean>(f)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val f: () -> Unit = {}
    checkSubtype<() -> Unit>(f)
}


// TESTCASE NUMBER: 5
fun case_5(x: (Int) -> String) {
    checkSubtype<(Int) -> String>(x)
}
