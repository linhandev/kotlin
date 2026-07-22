// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: if-else value equals the evaluated branch result
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val chosen = if (false) "NOK" else "OK"
    return chosen
}
