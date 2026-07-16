/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, overview -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: extension operator fun A.plus resolves in a + b and produces correct result
 */

// TESTCASE NUMBER: 1
class A9022(val n: Int)

operator fun A9022.plus(b: A9022) = A9022(n + b.n)

fun box(): String {
    val result = A9022(5) + A9022(7)
    return if (result.n == 12) "OK" else "NOK: ${result.n}"
}
