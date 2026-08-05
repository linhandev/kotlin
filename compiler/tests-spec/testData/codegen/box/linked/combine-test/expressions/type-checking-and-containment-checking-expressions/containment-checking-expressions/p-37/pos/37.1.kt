// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 37 -> sentence 37
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 37 -> sentence 37
 *                type-system, introduction-1 -> paragraph 37 -> sentence 37
 * NUMBER: 1
 * DESCRIPTION: not-in operator with nullable Int? element on List<Int?> at runtime
 */

// TESTCASE NUMBER: 1
fun testNull(xs: List<Int?>): Boolean = null !in xs

fun testPresent(xs: List<Int?>, x: Int): Boolean = x !in xs

fun box(): String {
    val xs: List<Int?> = listOf(1, 2)
    if (!testNull(xs)) return "NOK: null not in list"
    if (testPresent(xs, 1)) return "NOK: present element should not satisfy !in"
    if (!testPresent(xs, 3)) return "NOK: absent element should satisfy !in"
    return "OK"
}
