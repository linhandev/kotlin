// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: not-in operator with extension contains function resolves correctly at runtime
 */

// TESTCASE NUMBER: 1
class Box(val list: List<Int>)
operator fun Box.contains(x: Int): Boolean = x in list

fun test(x: Int): Boolean = x !in Box(listOf(1, 2, 3))

fun box(): String {
    if (test(2)) return "NOK"
    if (!test(4)) return "NOK"
    if (test(1)) return "NOK"
    if (!test(5)) return "NOK"
    return "OK"
}
