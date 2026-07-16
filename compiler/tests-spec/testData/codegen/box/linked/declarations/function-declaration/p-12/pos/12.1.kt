// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: vararg default values at runtime
 */

// TESTCASE NUMBER: 1
fun sum(vararg xs: Int = intArrayOf(2, 3)): Int = xs.sum()

fun box(): String {
    return if (sum() == 5 && sum(4, 5) == 9) "OK" else "NOK"
}
