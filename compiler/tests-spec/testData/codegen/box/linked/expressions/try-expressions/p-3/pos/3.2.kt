// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 2
 * DESCRIPTION: finally block is evaluated after try body when no exception is thrown
 */

// TESTCASE NUMBER: 1

fun box(): String {
    var finallyRan = false
    val v = try {
        1
    } catch (e: Exception) {
        2
    } finally {
        finallyRan = true
    }
    return if (v == 1 && finallyRan) "OK" else "NOK"
}
