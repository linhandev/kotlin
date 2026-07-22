// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: boolean condition with bound value is checked for equality not used directly
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return when (true) {
        false -> "NOK"
        true -> "OK"
        else -> "NOK"
    }
}
