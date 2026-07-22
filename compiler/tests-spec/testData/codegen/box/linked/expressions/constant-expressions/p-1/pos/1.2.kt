// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, constant-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: when branch comparing enum entry is compile-time constant
 */

// TESTCASE NUMBER: 1

enum class E { A, B }

fun box(): String {
    return when (E.A) {
        E.A -> "OK"
        E.B -> "NOK"
    }
}
