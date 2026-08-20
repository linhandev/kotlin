// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 91 -> sentence 91
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 91 -> sentence 91
 * NUMBER: 1
 * DESCRIPTION: member Double property == infers Boolean
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1

class C(val d: Double)

fun test(a: C, b: C): Boolean = a.d == b.d

fun case1() {
    checkSubtype<Boolean>(test(C(Double.NaN), C(Double.NaN)))
}
