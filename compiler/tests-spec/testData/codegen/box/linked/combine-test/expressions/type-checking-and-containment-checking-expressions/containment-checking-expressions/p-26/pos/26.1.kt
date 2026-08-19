// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: expressions, conditional-expressions -> paragraph 26 -> sentence 26
 *                operator-overloading, overview -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: conditional expression chooses in or not-in branch at runtime
 */

// TESTCASE NUMBER: 1
fun test(pickIn: Boolean, xs: List<Int>): Boolean = if (pickIn) 2 in xs else 4 !in xs

fun box(): String {
    val xs = listOf(1, 2, 3)
    if (!test(true, xs)) return "NOK: in branch for present element"
    if (test(true, listOf(4, 5))) return "NOK: in branch for absent element"
    if (!test(false, xs)) return "NOK: not-in branch for absent element"
    if (test(false, listOf(4))) return "NOK: not-in branch when 4 is only element"
    return "OK"
}
