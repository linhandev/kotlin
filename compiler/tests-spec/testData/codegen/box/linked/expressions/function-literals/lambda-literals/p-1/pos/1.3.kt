// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: lambda with explicit zero-parameter list before arrow
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val f: () -> Int = { -> 42 }
    if (f() != 42) return "NOK"
    return "OK"
}
