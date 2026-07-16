// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 3
 * DESCRIPTION: mutating values() array does not change enum constants
 */

// TESTCASE NUMBER: 1
enum class State { A, B }

fun box(): String {
    val first = State.values()
    first[0] = State.B
    val second = State.values()
    return if (second[0] == State.A && first[0] == State.B) "OK" else "NOK"
}
