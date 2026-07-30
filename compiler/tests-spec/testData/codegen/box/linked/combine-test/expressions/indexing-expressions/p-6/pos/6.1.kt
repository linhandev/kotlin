// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 6 -> sentence 6
 *                statements, assignments, simple-assignments -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: MutableList index assign via set
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val xs = mutableListOf(1, 2)
    xs[1] = 5
    return xs[1]
}

fun box(): String {
    if (test() != 5) return "NOK"
    return "OK"
}
