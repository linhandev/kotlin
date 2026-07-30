/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, comparison-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: less-than resolves via Comparable compareTo
 */

// TESTCASE NUMBER: 1
data class Ver(val n: Int) : Comparable<Ver> {
    override fun compareTo(other: Ver): Int = n.compareTo(other.n)
}

fun test(): Boolean = Ver(1) < Ver(2)

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
