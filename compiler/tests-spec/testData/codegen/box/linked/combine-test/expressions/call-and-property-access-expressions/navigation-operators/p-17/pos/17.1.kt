// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, navigation-operators -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                expressions, call-and-property-access-expressions, navigation-operators -> paragraph 17 -> sentence 17
 *                expressions, indexing-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: safe call on nullable IntArray accesses element by index, null receiver short-circuits to null
 */

// TESTCASE NUMBER: 1
fun test(a: IntArray?): Int? = a?.get(0)

fun box(): String {
    val arr = intArrayOf(42, 99)
    if (test(arr) != 42) return "NOK: non-null returns first element"
    if (test(null) != null) return "NOK: null returns null"
    return "OK"
}
