/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, building-the-overload-candidate-set, infix-function-call -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: infix OCS includes only callables with infix modifier
 */

class Pair11203(val left: String, val right: String) {
    infix fun join11203(other: Pair11203): String = left + other.left + right + other.right
}

// TESTCASE NUMBER: 1
fun box(): String {
    val a = Pair11203("O", "1")
    val b = Pair11203("K", "2")
    val result = a join11203 b
    return if (result == "OK12") "OK" else "NOK: $result"
}
