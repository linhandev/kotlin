// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, call-and-property-access-expressions, spread-operator-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: sum(*intArrayOf(1,2,3), 4) spreads IntArray into vararg and sums to 10
 */

// TESTCASE NUMBER: 1

fun sum(vararg xs: Int): Int = xs.sum()

fun box(): String {
    val arr = intArrayOf(1, 2, 3)
    if (sum(*arr, 4) != 10) return "NOK"
    return "OK"
}
