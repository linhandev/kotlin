// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 7
 * DESCRIPTION: sealed direct subtypes do not participate in exhaustiveness check
 */

// TESTCASE NUMBER: 1

sealed interface I1
sealed interface I2
sealed interface I3

class D1 : I1, I2
class D2 : I1, I3

sealed class D3 : I1, I3

fun box(): String {
    val b: I1 = D2()
    return when (b) {
        !is I3 -> "D1"
        is D2 -> "OK"
    }
}
