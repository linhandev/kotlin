/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: custom equals affects equality comparison
 */

// TESTCASE NUMBER: 1
class P(val x: Int) {
    override fun equals(other: Any?): Boolean = other is P && x == other.x
}

fun test(): Boolean = P(1) == P(1)

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
