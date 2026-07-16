// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: try-catch-finally runs catch on exception and always runs finally
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var s = "NOK"
    try {
        s = "OK"
    } catch (_: Exception) {
        s = "ERR"
    } finally {
    }
    return s
}
