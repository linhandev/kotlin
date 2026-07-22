// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, lambda-literals -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: lambda literal with explicit parameter list
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val f: (Int) -> Int = { x -> x + 1 }
    if (f(2) != 3) return "NOK"
    return "OK"
}
