// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: containment operators in and not-in check membership
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val xs = listOf(1, 2, 3)
    if (2 in xs && 4 !in xs) return "OK"
    return "NOK"
}
