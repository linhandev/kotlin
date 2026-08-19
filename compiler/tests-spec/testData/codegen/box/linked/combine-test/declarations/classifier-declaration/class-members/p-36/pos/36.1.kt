// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 36 -> sentence 36
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 36 -> sentence 36
 *                expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 36 -> sentence 36
 * NUMBER: 1
 * DESCRIPTION: class member contains enables !in expression
 */

// TESTCASE NUMBER: 1
class Box(val items: List<Int>) {
    operator fun contains(item: Int) = item in items
}

fun test(): Boolean = 4 !in Box(listOf(1, 2, 3))

fun box(): String {
    if (!test()) return "NOK"
    if (2 !in Box(listOf(1, 2, 3))) return "NOK"
    return "OK"
}
