// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: expressions, multiplicative-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: Wrapper(2) * 3 yields Product via custom operator times
 */

// TESTCASE NUMBER: 1

class Product(val left: Int, val right: Int)

class Wrapper(val value: Int) {
    operator fun times(other: Int): Product = Product(value, other)
}

fun box(): String {
    val r: Product = Wrapper(2) * 3
    return if (r.left == 2 && r.right == 3) "OK" else "NOK"
}
