// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 17 -> sentence 17
 *                expressions, indexing-expressions -> paragraph 17 -> sentence 17
 *                statements, assignments, simple-assignments -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: class member operator fun set enables multi-index write
 */

// TESTCASE NUMBER: 1
class MutableMatrix(private val data: MutableList<MutableList<Int>>) {
    operator fun get(row: Int, col: Int) = data[row][col]
    operator fun set(row: Int, col: Int, value: Int) { data[row][col] = value }
}

fun test(): Int {
    val m = MutableMatrix(mutableListOf(mutableListOf(1), mutableListOf(2)))
    m[0, 0] = 42
    return m[0, 0]
}

fun box(): String {
    if (test() != 42) return "NOK"
    return "OK"
}
