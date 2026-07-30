// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 33 -> sentence 33
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 33 -> sentence 33
 *                expressions, indexing-expressions -> paragraph 33 -> sentence 33
 *                statements, assignments, simple-assignments -> paragraph 33 -> sentence 33
 * NUMBER: 1
 * DESCRIPTION: class member operator fun set with single index
 */

// TESTCASE NUMBER: 1
class MutableWrapper(val items: MutableList<Int>) {
    operator fun get(index: Int) = items[index]
    operator fun set(index: Int, value: Int) { items[index] = value }
}

fun test(): Int {
    val w = MutableWrapper(mutableListOf(1, 2))
    w[0] = 42
    return w[0]
}

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
