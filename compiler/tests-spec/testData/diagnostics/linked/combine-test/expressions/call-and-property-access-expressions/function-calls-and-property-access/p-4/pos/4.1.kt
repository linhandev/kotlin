// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 4 -> sentence 4
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: trailing lambda after regular arguments type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun fold(init: Int, xs: List<Int>, acc: (Int, Int) -> Int): Int = xs.fold(init, acc)

fun case1() {
    checkSubtype<Int>(fold(0, listOf(1, 2)) { a, b -> a + b })
}
