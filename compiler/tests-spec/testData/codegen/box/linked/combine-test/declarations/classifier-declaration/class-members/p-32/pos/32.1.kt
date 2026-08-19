// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 32 -> sentence 32
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 32 -> sentence 32
 *                expressions, indexing-expressions -> paragraph 32 -> sentence 32
 * NUMBER: 1
 * DESCRIPTION: class member operator fun get with single index
 */

// TESTCASE NUMBER: 1
class Wrapper(val items: List<Int>) {
    operator fun get(index: Int) = items[index]
}

fun test(): Int = Wrapper(listOf(1, 2, 3))[1]

fun box(): String {
    if (test() != 2) return "NOK"
    if (Wrapper(listOf(1, 2, 3))[0] != 1) return "NOK"
    return "OK"
}
