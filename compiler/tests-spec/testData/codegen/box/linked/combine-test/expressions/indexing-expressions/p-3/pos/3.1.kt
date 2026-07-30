// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 3 -> sentence 3
 *                statements, assignments, simple-assignments -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: IntArray index read and write
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val a = intArrayOf(1, 2)
    a[0] = 3
    return a[0]
}

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
