// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: contains extension on nullable receiver requires smart cast to non-null Box before in operator resolves at runtime
 */

// TESTCASE NUMBER: 1
class Box(val list: List<Int>?)

operator fun Box.contains(x: Int): Boolean = x in (list ?: emptyList())

fun test(box: Box?): Boolean = if (box != null) 5 in box else false

fun box(): String {
    if (test(null)) return "NOK: null receiver must not smart cast"
    if (!test(Box(listOf(1, 5, 9)))) return "NOK: list containing 5"
    if (test(Box(listOf(1, 2, 3)))) return "NOK: list without 5"
    if (test(Box(null))) return "NOK: null list falls back to empty via elvis"
    return "OK"
}
