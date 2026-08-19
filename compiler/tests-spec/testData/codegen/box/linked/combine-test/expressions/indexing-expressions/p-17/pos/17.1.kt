// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 17 -> sentence 17
 *                statements, assignments, simple-assignments -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: index assign statement and set returns Unit; final element is 3
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val a = arrayOf(1)
    a[0] = 2
    val u: Unit = a.set(0, 3)
    return a[0]
}

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
