// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, anonymous-function-declarations -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: anonymous function with inferred parameter and return types from context
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val f: (Int) -> String = fun(x) = x.toString()
    if (f(7) != "7") return "NOK"
    return "OK"
}
