// WITH_STDLIB
/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 89 -> sentence 89
 * PRIMARY LINKS: expressions, equality-expressions -> paragraph 89 -> sentence 89
 * NUMBER: 1
 * DESCRIPTION: == agrees with equals for plain class default equals
 */

// TESTCASE NUMBER: 1
class Box(val x: Int)

fun test(a: Box, b: Box): Boolean = (a == b) == a.equals(b)

fun box(): String {
    val a = Box(1)
    val b = Box(1)
    if (!test(a, a)) return "NOK"
    if (!test(a, b)) return "NOK"
    return "OK"
}
