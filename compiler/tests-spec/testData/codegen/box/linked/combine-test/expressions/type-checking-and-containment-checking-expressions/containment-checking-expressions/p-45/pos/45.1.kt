// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 45 -> sentence 45
 * PRIMARY LINKS: expressions, try-expressions -> paragraph 45 -> sentence 45
 *                operator-overloading, overview -> paragraph 45 -> sentence 45
 * NUMBER: 1
 * DESCRIPTION: in operator inside try expression at runtime
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Int>): Boolean = try { 2 in xs } catch (_: Exception) { false }

fun box(): String {
    if (!test(listOf(1, 2, 3))) return "NOK: present element in try"
    if (test(listOf(4, 5))) return "NOK: absent element in try"
    return "OK"
}
