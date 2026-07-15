// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, built-in-types, kotlin.function -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 1 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Lambda, member reference, and extension function types are subtypes of kotlin.Function(R)
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1() {
    checkSubtype<Function<Unit>> {}
    val f: Function<Unit> = {}
}


// TESTCASE NUMBER: 2
fun case_2() {
    checkSubtype<Function<String>> { x: Int -> x.toString() }
    val f: Function<String> = { x: Int -> x.toString() }
}


// TESTCASE NUMBER: 3
fun case_3() {
    checkSubtype<Function<Boolean>> { x: Int, y: String -> x.toString() == y }
    val f: Function<Boolean> = { x: Int, y: String -> x.toString() == y }
}


// TESTCASE NUMBER: 4
fun case_4(): Int = 42

fun case_4_use() {
    checkSubtype<Function<Int>>(::case_4)
    val f: Function<Int> = ::case_4
}


// TESTCASE NUMBER: 5
fun Int.case_5(): String = toString()

fun case_5_use() {
    val ext: Int.() -> String = Int::case_5
    checkSubtype<Function<String>>(ext)
    val f: Function<String> = ext
}
