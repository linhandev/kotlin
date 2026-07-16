// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, conditional-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: false condition evaluates false branch
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return if (false) "NOK" else "OK"
}
