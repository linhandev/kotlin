// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 92 -> sentence 92
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 92 -> sentence 92
 * NUMBER: 1
 * DESCRIPTION: member Double.NaN boxed as Any uses equals
 */

// TESTCASE NUMBER: 1

class C(val d: Double)

fun test(a: C, b: C): Boolean = (a.d as Any) == (b.d as Any)

fun box(): String {
    if (!test(C(Double.NaN), C(Double.NaN))) return "NOK"
    return "OK"
}
