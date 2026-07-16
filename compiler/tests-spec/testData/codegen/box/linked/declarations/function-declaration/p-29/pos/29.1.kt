// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: tail-recursive factorialTC computes correct result at runtime
 */

// TESTCASE NUMBER: 1
tailrec fun factorialTC(i: Int, result: Int = 1): Int {
    if (i == 0) return result
    return factorialTC(i - 1, i * result)
}

fun box(): String {
    return if (factorialTC(5) == 120) "OK" else "NOK"
}
