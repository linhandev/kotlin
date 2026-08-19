// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 88 -> sentence 88
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 88 -> sentence 88
 * NUMBER: 1
 * DESCRIPTION: custom equals drives structural ==
 */

// TESTCASE NUMBER: 1
class Box(val x: Int) {
    override fun equals(other: Any?): Boolean = other is Box && x == other.x
}

fun test(): Boolean = Box(42) == Box(42)

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
