// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 5
 * DESCRIPTION: try with caught exception yields last value from matching catch block
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val v = try {
        throw Exception()
    } catch (e: Exception) {
        "OK"
    }
    return v
}
