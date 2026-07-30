// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 2 -> sentence 2
 *                statements, assignments, simple-assignments -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Array index assign via set
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val a = arrayOf(1, 2)
    a[1] = 9
    return a[1]
}

fun box(): String {
    if (test() != 9) return "NOK"
    return "OK"
}
