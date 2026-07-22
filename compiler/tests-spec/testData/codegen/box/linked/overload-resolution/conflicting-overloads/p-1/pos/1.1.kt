/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, conflicting-overloads -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: non-conflicting overloads with distinguishable parameter types resolve and run
 */

fun ok118(x: Int): Int = x
fun ok118(x: String): String = x

// TESTCASE NUMBER: 1
fun box(): String {
    if (ok118(1) != 1) return "NOK"
    if (ok118("a") != "a") return "NOK"
    return "OK"
}
