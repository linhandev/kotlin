// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: extension operator get enables multi-index access
 */

// TESTCASE NUMBER: 1
operator fun List<Int>.get(i: Int, j: Int): Int = this[i] + this[j]

fun test(): Int = listOf(1, 9)[0, 1]

fun box(): String {
    if (test() != 10) return "NOK"
    return "OK"
}
