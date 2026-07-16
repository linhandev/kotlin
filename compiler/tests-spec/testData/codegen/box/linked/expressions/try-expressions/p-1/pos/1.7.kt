// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 7
 * DESCRIPTION: try { "try-body" } finally {} returns try value with empty finally
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val v = try {
        "try-body"
    } finally {
    }
    return if (v == "try-body") "OK" else "NOK"
}
