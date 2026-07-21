/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, overview -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: member operator fun plus with heterogeneous operand types resolves in a + b
 */

// TESTCASE NUMBER: 1
class A9021(val n: Int) {
    operator fun plus(b: B9021) = A9021(n + b.n)
}

class B9021(val n: Int)

fun box(): String {
    val result = A9021(10) + B9021(32)
    return if (result.n == 42) "OK" else "NOK: ${result.n}"
}
