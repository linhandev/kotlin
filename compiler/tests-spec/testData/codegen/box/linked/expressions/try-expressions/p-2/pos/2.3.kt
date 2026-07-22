// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: first matching catch block is picked when several catch blocks match
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return try {
        throw RuntimeException()
    } catch (_: Exception) {
        "OK"
    } catch (_: RuntimeException) {
        "NOK"
    }
}
