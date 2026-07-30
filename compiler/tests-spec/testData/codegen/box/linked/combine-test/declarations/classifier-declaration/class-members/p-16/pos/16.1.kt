// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 16 -> sentence 16
 *                expressions, indexing-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: class member operator fun get enables multi-index read
 */

// TESTCASE NUMBER: 1
class Matrix(private val data: List<List<Int>>) {
    operator fun get(row: Int, col: Int) = data[row][col]
}

fun test(): Int = Matrix(listOf(listOf(1, 2), listOf(3, 4)))[0, 1]

fun box(): String {
    if (test() != 2) return "NOK"
    if (Matrix(listOf(listOf(1, 2), listOf(3, 4)))[1, 0] != 3) return "NOK"
    return "OK"
}
