// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 3
 * DESCRIPTION: enum valueOf with illegal name throws at runtime
 */

// TESTCASE NUMBER: 1
enum class State { A, B }

fun box(): String {
    try {
        State.valueOf("Foo")
        return "NOK"
    } catch (_: IllegalArgumentException) {
        return "OK"
    }
}
