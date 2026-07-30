// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 92 -> sentence 92
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 92 -> sentence 92
 * NUMBER: 1
 * DESCRIPTION: member Double boxed as Any == infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class C(val d: Double)

fun test(a: C, b: C): Boolean = (a.d as Any) == (b.d as Any)

fun case1() {
    checkSubtype<Boolean>(test(C(Double.NaN), C(Double.NaN)))
}
