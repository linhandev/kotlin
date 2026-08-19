// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 21 -> sentence 21
 *                type-system, introduction-1 -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: nullable receiver uses safe get call
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>?): Int? = xs?.get(0)

fun box(): String {
    if (test(listOf(7, 8)) != 7) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
