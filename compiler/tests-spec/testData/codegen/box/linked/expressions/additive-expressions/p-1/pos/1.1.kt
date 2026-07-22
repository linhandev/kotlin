// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, additive-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Wrapper(1) + 2 yields Sum via custom operator plus
 */

// TESTCASE NUMBER: 1

class Sum(val left: Int, val right: Int)

class Wrapper(val value: Int) {
    operator fun plus(other: Int): Sum = Sum(value, other)
}

fun box(): String {
    val r: Sum = Wrapper(1) + 2
    return if (r.left == 1 && r.right == 2) "OK" else "NOK"
}
