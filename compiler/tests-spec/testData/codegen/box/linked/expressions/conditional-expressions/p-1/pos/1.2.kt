// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: outer if uses nested if (1 < 2) true else false as condition
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return if (if (1 < 2) true else false) "OK" else "NOK"
}
