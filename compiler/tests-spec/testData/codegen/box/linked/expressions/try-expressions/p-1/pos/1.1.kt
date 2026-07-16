// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: try returns "try-body" when no exception is thrown and catch is skipped
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val result = try {
        "try-body"
    } catch (_: Exception) {
        "NOK"
    }
    return if (result == "try-body") "OK" else "NOK"
}
