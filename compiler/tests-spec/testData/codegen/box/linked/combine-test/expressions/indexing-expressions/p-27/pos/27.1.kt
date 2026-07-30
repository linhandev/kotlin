// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 27 -> sentence 27
 *                statements, assignments, simple-assignments -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: indexed postfix ++ uses get and set
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val a = intArrayOf(1)
    val old = a[0]++
    return old * 10 + a[0]
}

fun box(): String {
    if (test() != 12) return "NOK"
    return "OK"
}
