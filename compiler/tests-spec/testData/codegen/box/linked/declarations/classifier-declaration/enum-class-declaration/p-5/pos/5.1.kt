// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: enum values returns a fresh array on each call at runtime
 */

// TESTCASE NUMBER: 1
enum class State { A, B, C }

fun box(): String {
    val a1 = State.values()
    val a2 = State.values()
    return if (a1 !== a2 && a1.size == 3 && a1[1] == State.B) "OK" else "NOK"
}
