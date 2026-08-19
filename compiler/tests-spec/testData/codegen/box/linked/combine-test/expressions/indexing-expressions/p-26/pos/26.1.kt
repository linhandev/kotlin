// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 26 -> sentence 26
 *                statements, assignments, simple-assignments -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: indexed plusAssign uses get and set
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val a = intArrayOf(1)
    a[0] += 2
    return a[0]
}

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
