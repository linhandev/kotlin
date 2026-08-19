// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, elvis-operator-expressions -> paragraph 21 -> sentence 21
 *                type-system, introduction-1 -> paragraph 21 -> sentence 21
 *                operator-overloading, overview -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: in operator on Elvis fallback nullable List receiver at runtime
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>?): Boolean = 2 in (xs ?: listOf(1, 2, 3))

fun box(): String {
    if (!test(null)) return "NOK: null list fallback should contain 2"
    if (test(listOf(4, 5))) return "NOK: absent element in non-null list"
    if (!test(listOf(1, 2))) return "NOK: present element in non-null list"
    return "OK"
}
