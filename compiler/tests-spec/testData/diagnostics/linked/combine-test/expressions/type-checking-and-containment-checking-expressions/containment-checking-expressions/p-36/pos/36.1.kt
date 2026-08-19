// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: expressions, multiplicative-expressions -> paragraph 36 -> sentence 36
 *                operator-overloading, overview -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: multiplicative expression with in operator infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(): Boolean = (2 * 3) in listOf(6, 7) && (2 * 4) !in listOf(6, 7)

fun case2() {
    checkSubtype<Boolean>(case1())
    checkSubtype<Boolean>((2 * 5) in listOf(6, 7))
}
