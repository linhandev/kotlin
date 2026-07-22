// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, try-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: try returning 1 or catch 2L assigns Number type with value 1
 */

// TESTCASE NUMBER: 1

fun box(): String {
    val v: Number = try {
        1
    } catch (e: Exception) {
        2L
    }
    return if (v == 1) "OK" else "NOK"
}
