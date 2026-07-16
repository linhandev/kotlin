// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: try-finally executes try body and always runs finally block
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var s = "NOK"
    try {
        s = "OK"
    } finally {
    }
    return s
}
