// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: try with two catch clauses returns try value when no exception is thrown
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val result = try {
        "try-body"
    } catch (_: IllegalArgumentException) {
        "NOK-1"
    } catch (_: Exception) {
        "NOK-2"
    }
    return if (result == "try-body") "OK" else "NOK"
}
