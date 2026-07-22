// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, function-literals, lambda-literals -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: { it + 1 } single-parameter lambda uses implicit it
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val f: (Int) -> Int = { it + 1 }
    if (f(4) != 5) return "NOK"
    return "OK"
}
