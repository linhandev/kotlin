// FIR_IDENTICAL
// WITH_STDLIB
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: return@forEach is local return and does not exit enclosing function
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case_1(xs: List<Int>): Int {
    xs.forEach { if (it < 0) return@forEach }
    return xs.size
}

fun case_1_check() {
    checkSubtype<Int>(case_1(listOf(1, -2, 3)))
}
