// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 25 -> sentence 25
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: in operator on Map with Int operand checks keys not values and infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val m = mapOf<Any, Int>("a" to 1)
    checkSubtype<Boolean>(1 in m)
    checkSubtype<Boolean>(1 in m == m.containsKey(1))
}
