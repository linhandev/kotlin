// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: in operator desugars to contains call and works correctly at runtime
 */

// TESTCASE NUMBER: 1
class Box(val list: List<Int>)

var containsCalls = 0

operator fun Box.contains(x: Int): Boolean {
    containsCalls++
    return x in list
}

fun test(x: Int): Boolean = x in Box(listOf(1, 2, 3))

fun box(): String {
    containsCalls = 0
    if (!test(2)) return "NOK: member found"
    if (containsCalls != 1) return "NOK: in did not invoke contains for true branch"
    containsCalls = 0
    if (test(4)) return "NOK: member not found"
    if (containsCalls != 1) return "NOK: in did not invoke contains for false branch"
    return "OK"
}
