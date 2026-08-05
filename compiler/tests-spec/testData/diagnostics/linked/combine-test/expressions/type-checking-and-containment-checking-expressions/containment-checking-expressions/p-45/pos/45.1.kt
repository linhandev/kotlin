// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 45 -> sentence 45
 * PRIMARY LINKS: expressions, try-expressions -> paragraph 45 -> sentence 45
 *                operator-overloading, overview -> paragraph 45 -> sentence 45
 * NUMBER: 1
 * DESCRIPTION: try expression containing in operator infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(xs: List<Int>): Boolean = try { 2 in xs } catch (_: Exception) { false }

fun case2() {
    checkSubtype<Boolean>(case1(listOf(1, 2, 3)))
    checkSubtype<Boolean>(case1(listOf(4, 5)))
}
