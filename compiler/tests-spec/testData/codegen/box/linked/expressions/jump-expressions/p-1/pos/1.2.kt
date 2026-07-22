// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: throw has kotlin.Nothing type and never completes normally
 */

// TESTCASE NUMBER: 1

fun fail(): Nothing = throw IllegalStateException()

fun box(): String {
    return try {
        fail()
        "NOK"
    } catch (_: IllegalStateException) {
        "OK"
    }
}
