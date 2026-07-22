// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, type-checking-and-containment-checking-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: inOperator containment with in not-in and newline
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val xs = listOf(1, 2, 3)
    return if (2 in xs && 4
        !in xs) "OK" else "NOK"
}
