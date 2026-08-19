// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: expressions, string-interpolation-expressions -> paragraph 31 -> sentence 31
 *                operator-overloading, overview -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: string interpolation result with in operator infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Int): Boolean = "$x" in listOf("1", "2", "10")

fun case2() {
    checkSubtype<Boolean>(case1(1))
    checkSubtype<Boolean>(case1(3))
}
