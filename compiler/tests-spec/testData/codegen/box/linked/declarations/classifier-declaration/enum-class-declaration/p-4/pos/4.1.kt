// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: enum entries property returns all constants with correct size and order at runtime
 */

// TESTCASE NUMBER: 1
enum class State { A, B, C }

fun box(): String {
    val entries = State.entries
    return if (entries.size == 3 && entries[0] == State.A && entries[2] == State.C) "OK" else "NOK"
}
