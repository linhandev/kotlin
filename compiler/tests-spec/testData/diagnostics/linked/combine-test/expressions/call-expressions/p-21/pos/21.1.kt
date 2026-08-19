// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 21 -> sentence 21
 *                overload-resolution, building-the-overload-candidate-set, call-with-named-parameters -> paragraph 21 -> sentence 21
 *                expressions, function-literals, lambda-literals -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: named arguments before trailing lambda do not prevent trailing lambda from binding to last parameter
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun fold(init: Int = 0, xs: List<Int>, op: (Int, Int) -> Int): Int = xs.fold(init, op)

fun case_1() {
    checkSubtype<Int>(fold(xs = listOf(1, 2)) { a, b -> a + b })
}
