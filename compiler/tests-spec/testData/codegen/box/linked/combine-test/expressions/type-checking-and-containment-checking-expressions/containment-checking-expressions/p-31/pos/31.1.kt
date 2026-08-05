// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 31 -> sentence 31
 * PRIMARY LINKS: expressions, string-interpolation-expressions -> paragraph 31 -> sentence 31
 *                operator-overloading, overview -> paragraph 31 -> sentence 31
 * NUMBER: 1
 * DESCRIPTION: string interpolation result used as in left operand at runtime
 */

// TESTCASE NUMBER: 1
fun test(x: Int): Boolean = "$x" in listOf("1", "2", "10")

fun box(): String {
    if (!test(1)) return "NOK: interpolated 1 found"
    if (test(3)) return "NOK: interpolated 3 not in list"
    if (!test(10)) return "NOK: interpolated 10 found"
    if (!test(2)) return "NOK: interpolated 2 not found"
    return "OK"
}
