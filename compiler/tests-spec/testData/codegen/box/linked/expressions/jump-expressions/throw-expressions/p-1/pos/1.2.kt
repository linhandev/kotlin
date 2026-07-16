// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, jump-expressions, throw-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: throw on elvis RHS throws the supplied exception value
 */

// TESTCASE NUMBER: 1

fun box(): String {
    return try {
        val x: Int? = null
        val y: Int = x ?: throw IllegalStateException()
        y.toString()
    } catch (_: IllegalStateException) {
        "OK"
    }
}
