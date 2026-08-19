// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: custom equals drives structural ==
 */

// TESTCASE NUMBER: 1
class C(val v: Int) {
    override fun equals(other: Any?): Boolean = other is C && v == other.v
}

fun test(): Boolean = C(1) == C(1)

fun box(): String {
    if (!test()) return "NOK"
    if (C(1) == C(2)) return "NOK"
    return "OK"
}
