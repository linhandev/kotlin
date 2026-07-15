// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, suspending-function-types -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Suspending and non-suspending function types are assignable within their own kind
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun foo1(i: Int): String = i.toString()

fun case_1() {
    val fooRef: (Int) -> String = ::foo1
    val fooLambda: (Int) -> String = { it.toString() }
    val suspendFooLambda: suspend (Int) -> String = { it.toString() }
}


// TESTCASE NUMBER: 2
fun case_2() {
    val f: suspend () -> Unit = { }
    checkSubtype<suspend () -> Unit>(f)
}


// TESTCASE NUMBER: 3
fun case_3() {
    val f: suspend (Int, String) -> Boolean = { x, y -> x.toString() == y }
    checkSubtype<suspend (Int, String) -> Boolean>(f)
}


// TESTCASE NUMBER: 4
suspend fun case_4(): Int = 42

fun case_4_use() {
    val f: suspend () -> Int = ::case_4
    checkSubtype<suspend () -> Int>(f)
}


// TESTCASE NUMBER: 5
fun case_5(f: suspend (Int) -> String) {
    checkSubtype<suspend (Int) -> String>(f)
}
