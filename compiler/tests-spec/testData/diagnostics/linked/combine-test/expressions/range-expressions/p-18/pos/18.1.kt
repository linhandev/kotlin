// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 18 -> sentence 18
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: Long in IntRange infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
val r = 1..10

fun case1(x: Long) {
    checkSubtype<Boolean>(x in r)
}
