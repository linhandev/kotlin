// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, anonymous-function-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: anonymous function with typed parameters and equals-sign body returns computed value
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val f = fun(x: Int): Int = x + 1
    if (f(2) != 3) return "NOK"
    return "OK"
}
