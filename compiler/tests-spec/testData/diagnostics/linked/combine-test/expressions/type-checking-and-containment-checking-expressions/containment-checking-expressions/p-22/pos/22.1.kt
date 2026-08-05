// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 22 -> sentence 22
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 22 -> sentence 22
 *                expressions, range-expressions -> paragraph 22 -> sentence 22
 *                operator-overloading, overview -> paragraph 22 -> sentence 22
 * NUMBER: 1
 * DESCRIPTION: is smart cast plus in range infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(x: Any): Boolean = if (x is Int) x in 1..10 else false

fun case2() {
    checkSubtype<Boolean>(case1(5))
    checkSubtype<Boolean>(case1("x"))
}
