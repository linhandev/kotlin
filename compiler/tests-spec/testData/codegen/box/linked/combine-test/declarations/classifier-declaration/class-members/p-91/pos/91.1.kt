// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 91 -> sentence 91
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 91 -> sentence 91
 * NUMBER: 1
 * DESCRIPTION: member Double property IEEE == is false for NaN
 */

// TESTCASE NUMBER: 1

class C(val d: Double)

fun test(a: C, b: C): Boolean = a.d == b.d

fun box(): String {
    if (test(C(Double.NaN), C(Double.NaN))) return "NOK"
    if (!test(C(1.0), C(1.0))) return "NOK"
    return "OK"
}
