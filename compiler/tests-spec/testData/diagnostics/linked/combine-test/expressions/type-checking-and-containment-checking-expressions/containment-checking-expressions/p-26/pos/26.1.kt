// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, conditional-expressions -> paragraph 26 -> sentence 26
 *                operator-overloading, overview -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: conditional expression with in and not-in branches infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(pickIn: Boolean, xs: List<Int>): Boolean = if (pickIn) 2 in xs else 4 !in xs

fun case2() {
    checkSubtype<Boolean>(case1(true, listOf(1, 2, 3)))
    checkSubtype<Boolean>(case1(false, listOf(1, 2, 3)))
}
