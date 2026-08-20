// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 17 -> sentence 17
 *                expressions, indexing-expressions -> paragraph 17 -> sentence 17
 *                statements, assignments, simple-assignments -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: class member operator fun set then get infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class MutableMatrix(private val data: MutableList<MutableList<Int>>) {
    operator fun get(row: Int, col: Int) = data[row][col]
    operator fun set(row: Int, col: Int, value: Int) { data[row][col] = value }
}

fun case1() {
    val m = MutableMatrix(mutableListOf(mutableListOf(1), mutableListOf(2)))
    m[0, 0] = 42
    checkSubtype<Int>(m[0, 0])
}
