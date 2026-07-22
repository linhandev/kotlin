// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, throw-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: throw exception object is caught by matching catch block
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return try {
        throw IllegalStateException("fail")
    } catch (_: IllegalStateException) {
        "OK"
    }
}
