/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, overview -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: a + b expands to member operator fun plus call and evaluates correctly
 */

// TESTCASE NUMBER: 1
class A(val n: Int) {
    operator fun plus(b: A) = A(n + b.n)
}

fun box(): String {
    val result = A(1) + A(2)
    return if (result.n == 3) "OK" else "NOK: ${result.n}"
}
