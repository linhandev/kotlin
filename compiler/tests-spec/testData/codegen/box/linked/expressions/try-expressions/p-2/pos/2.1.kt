// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: catch clause with typed exception parameter handles thrown exception
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return try {
        throw Exception("OK")
    } catch (e: Exception) {
        e.message ?: "NOK"
    }
}
