// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 90 -> sentence 90
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 90 -> sentence 90
 *                declarations, classifier-declaration, data-class-declaration -> paragraph 90 -> sentence 90
 * NUMBER: 1
 * DESCRIPTION: equals/hashCode consistent when equal; unequal equals false
 */

// TESTCASE NUMBER: 1

data class Data(val x: Int)

fun equalConsistent(a: Data, b: Data): Boolean =
    (a == b) && (a.hashCode() == b.hashCode())

fun box(): String {
    if (!equalConsistent(Data(42), Data(42))) return "NOK: equal"
    if (Data(42) == Data(10)) return "NOK: unequal"
    return "OK"
}
