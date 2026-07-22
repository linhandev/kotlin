// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: finally block runs after try body completes
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var ran = false
    try {
        ran = true
    } finally {
    }
    return if (ran) "OK" else "NOK"
}
