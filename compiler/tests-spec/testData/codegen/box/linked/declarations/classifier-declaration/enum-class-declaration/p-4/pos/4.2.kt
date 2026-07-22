// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: enum valueOf resolves constant by name at runtime
 */

// TESTCASE NUMBER: 1
enum class State { A, B }

fun box(): String = if (State.valueOf("B") == State.B) "OK" else "NOK"
