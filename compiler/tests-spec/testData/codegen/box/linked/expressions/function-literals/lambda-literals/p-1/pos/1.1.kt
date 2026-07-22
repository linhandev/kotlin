// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: lambda literal with no parameter list defines zero-parameter function in context
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val f: () -> Int = { 42 }
    if (f() != 42) return "NOK"
    return "OK"
}
