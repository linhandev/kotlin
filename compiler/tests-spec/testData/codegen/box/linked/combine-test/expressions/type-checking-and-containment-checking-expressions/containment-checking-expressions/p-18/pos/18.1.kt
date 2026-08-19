// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 18 -> sentence 18
 *                expressions, when-expressions -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: when subjectless branch with in operator resolves custom extension contains at runtime
 */

// TESTCASE NUMBER: 1
class Box(val list: List<Int>)

var containsCalls = 0

operator fun Box.contains(x: Int): Boolean {
    containsCalls++
    return x in list
}

fun test(x: Int): String = when {
    x in Box(listOf(1, 2, 3)) -> "found"
    else -> "not found"
}

fun box(): String {
    containsCalls = 0
    if (test(2) != "found") return "NOK: found branch"
    if (containsCalls != 1) return "NOK: contains not invoked in when branch"
    containsCalls = 0
    if (test(9) != "not found") return "NOK: else branch"
    if (containsCalls != 1) return "NOK: contains not invoked before else selection"
    return "OK"
}
