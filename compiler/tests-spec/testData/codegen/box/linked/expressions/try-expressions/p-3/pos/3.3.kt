// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: finally block runs but does not replace try-produced value
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val v = try {
        "OK"
    } finally {
        "NOK"
    }
    return if (v == "OK") "OK" else "NOK"
}
