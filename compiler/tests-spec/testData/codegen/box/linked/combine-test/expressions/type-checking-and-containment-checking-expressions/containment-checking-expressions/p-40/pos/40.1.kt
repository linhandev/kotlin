// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 40 -> sentence 40
 *                type-system, introduction-1 -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: custom Iterable with member operator contains supports in operator at runtime
 */

// TESTCASE NUMBER: 1
class MyColl(private val data: List<Int>) : Iterable<Int> {
    override fun iterator(): Iterator<Int> = data.iterator()
    operator fun contains(x: Int): Boolean = x in data
}

fun test(x: Int): Boolean = x in MyColl(listOf(1, 2))

fun box(): String {
    if (!test(2)) return "NOK: member found"
    if (test(3)) return "NOK: member not found"
    if (!test(1)) return "NOK: first member found"
    if (test(0)) return "NOK: zero not in collection"
    return "OK"
}
