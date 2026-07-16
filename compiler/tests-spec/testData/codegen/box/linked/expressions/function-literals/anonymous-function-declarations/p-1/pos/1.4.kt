// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, anonymous-function-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: anonymous function with block function body
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val f = fun(x: Int): Int {
        return x * 2
    }
    if (f(3) != 6) return "NOK"
    return "OK"
}
