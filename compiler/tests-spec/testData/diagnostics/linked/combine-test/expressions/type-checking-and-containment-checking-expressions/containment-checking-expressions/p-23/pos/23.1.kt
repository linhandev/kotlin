// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: expressions, logical-conjunction-expressions -> paragraph 23 -> sentence 23
 *                operator-overloading, overview -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: logical conjunction with in operator infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Int, xs: List<Int>): Boolean = x > 0 && x in xs

fun case2() {
    checkSubtype<Boolean>(case1(2, listOf(1, 2, 3)))
    checkSubtype<Boolean>(case1(-1, listOf(1, 2, 3)))
}
