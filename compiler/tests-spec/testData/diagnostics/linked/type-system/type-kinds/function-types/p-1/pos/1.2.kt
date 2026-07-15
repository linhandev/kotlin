// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, function-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, type-kinds, built-in-types, kotlin.function -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: Function types with receiver are equivalent to regular function types w.r.t. subtyping
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun Int.baz(x: Int): String = "$this-$x"

fun case_1() {
    val ext: Int.(Int) -> String = Int::baz
    val regular: (Int, Int) -> String = ext
    checkSubtype<(Int, Int) -> String>(ext)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val ext: Int.() -> String = { toString() }
    val regular: (Int) -> String = ext
    checkSubtype<(Int) -> String>(ext)
}


// TESTCASE NUMBER: 3
fun foo3(i: Number): Number = i

fun case_3() {
    val ref: (Number) -> Number = ::foo3
    val wider: (Int) -> Any = ref
    checkSubtype<(Int) -> Any>(ref)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val f: (Int) -> String = { it.toString() }
    checkSubtype<Function<String>>(f)
}


// TESTCASE NUMBER: 5
fun Number.case_5(): CharSequence = toString()

fun case_5() {
    val ref: Number.() -> CharSequence = Number::case_5
    val wider: (Int) -> CharSequence = ref
    checkSubtype<(Int) -> CharSequence>(ref)
}
