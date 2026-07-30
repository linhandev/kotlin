// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 12 -> sentence 12
 *                statements, assignments, simple-assignments -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: custom operator set enables index write
 */

// TESTCASE NUMBER: 1
class Box(val data: IntArray) {
    operator fun get(i: Int): Int = data[i]
    operator fun set(i: Int, v: Int) { data[i] = v }
}

fun test(): Int {
    val b = Box(intArrayOf(1))
    b[0] = 8
    return b[0]
}

fun box(): String {
    if (test() != 8) return "NOK"
    return "OK"
}
