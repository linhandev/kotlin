// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 38 -> sentence 38
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 38 -> sentence 38
 * NUMBER: 1
 * DESCRIPTION: return@map ends current element mapping without exiting function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(xs: List<Int>): List<Int> = xs.map { if (it < 0) return@map 0 else it * 2 }

fun case_1_check() {
    checkSubtype<List<Int>>(case_1(listOf(-1, 2)))
}
