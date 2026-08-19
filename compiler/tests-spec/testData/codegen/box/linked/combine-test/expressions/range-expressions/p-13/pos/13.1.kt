// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, range-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 13 -> sentence 13
 *                type-system, introduction-1 -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: nullable bound builds range via safe call
 */

// TESTCASE NUMBER: 1
fun test(lo: Int?): IntRange? = lo?.let { it..10 }

fun box(): String {
    val r = test(1) ?: return "NOK"
    if (r.first != 1 || r.last != 10) return "NOK"
    if (5 !in r) return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
