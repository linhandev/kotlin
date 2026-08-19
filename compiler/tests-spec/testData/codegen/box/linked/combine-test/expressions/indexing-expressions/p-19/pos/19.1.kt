// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: chained index read on 2D array
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val m = arrayOf(arrayOf(1, 2), arrayOf(3, 4))
    return m[1][0]
}

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
