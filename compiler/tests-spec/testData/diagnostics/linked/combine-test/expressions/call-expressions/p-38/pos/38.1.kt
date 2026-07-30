// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 38 -> sentence 38
 *                type-inference, introduction-1 -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: assignment and return contexts provide expected type for downward inference of type arguments
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun <T> empty(): List<T> = emptyList()

fun case_1() {
    val xs: List<String> = empty()
    checkSubtype<List<String>>(xs)
}

fun case_2() {
    val xs: List<Int> = empty()
    checkSubtype<List<Int>>(xs)
}
