/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: operator-overloading, overview -> paragraph 2 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: explicit operator function call produces the same result as operator syntax
 */

// TESTCASE NUMBER: 1
class A9023(val n: Int) {
    operator fun plus(b: A9023) = A9023(n + b.n)
}

fun box(): String {
    val a = A9023(1)
    val b = A9023(2)
    val viaSyntax = a + b
    val viaCall = a.plus(b)
    return if (viaSyntax.n == 3 && viaCall.n == 3) "OK" else "NOK: ${viaSyntax.n}, ${viaCall.n}"
}
