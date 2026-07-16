/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, operator-call -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: operator OCS includes only callables with operator modifier
 */

class A11204(val n: Int) {
    operator fun plus(b: A11204) = A11204(n + b.n)
}

// TESTCASE NUMBER: 1
fun box(): String {
    val result = A11204(2) + A11204(3)
    return if (result.n == 5) "OK" else "NOK: ${result.n}"
}
