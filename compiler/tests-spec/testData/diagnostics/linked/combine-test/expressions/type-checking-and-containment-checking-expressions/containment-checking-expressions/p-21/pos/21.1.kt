// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, elvis-operator-expressions -> paragraph 21 -> sentence 21
 *                type-system, introduction-1 -> paragraph 21 -> sentence 21
 *                operator-overloading, overview -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: Elvis fallback nullable List receiver with in operator infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(xs: List<Int>?): Boolean = 2 in (xs ?: listOf(1, 2, 3))

fun case2() {
    checkSubtype<Boolean>(case1(null))
    checkSubtype<Boolean>(case1(listOf(1, 2)))
}
