// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: expressions, additive-expressions -> paragraph 32 -> sentence 32
 *                expressions, range-expressions -> paragraph 32 -> sentence 32
 *                operator-overloading, overview -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: additive expression with in range infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(): Boolean = (1 + 2) in 1..10 && (1 + 10) !in 1..5

fun case2() {
    checkSubtype<Boolean>(case1())
    checkSubtype<Boolean>((1 + 11) in 1..10)
}
