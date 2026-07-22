// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: catch block matches when exception parameter type is supertype of thrown exception
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return try {
        throw IllegalArgumentException()
    } catch (e: Exception) {
        "OK"
    }
}
