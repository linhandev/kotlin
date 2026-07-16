// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, enum-class-declaration -> paragraph 5 -> sentence 5
 * NUMBER: 2
 * DESCRIPTION: enumValues returns all enum constants in declaration order at runtime
 */

// TESTCASE NUMBER: 1
enum class State { X, Y }

fun box(): String {
    val arr = enumValues<State>()
    return if (arr.size == 2 && arr[0] == State.X) "OK" else "NOK"
}
