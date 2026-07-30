// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 19 -> sentence 19
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: class member operator fun contains enables in expression
 */

// TESTCASE NUMBER: 1
class Box(val items: List<Int>) {
    operator fun contains(item: Int) = item in items
}

fun test(): Boolean = 2 in Box(listOf(1, 2, 3))

fun box(): String {
    if (!test()) return "NOK"
    if (4 in Box(listOf(1, 2, 3))) return "NOK"
    if (!(5 !in Box(listOf(1, 2, 3)))) return "NOK"
    return "OK"
}
