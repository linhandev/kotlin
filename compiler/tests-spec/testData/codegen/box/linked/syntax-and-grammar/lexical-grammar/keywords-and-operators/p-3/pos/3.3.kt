// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 3 -> sentence 3
 * NUMBER: 3
 * DESCRIPTION: COMMA token used in destructuring declaration val (a, b) = pair
 */

data class Point(val x: Int, val y: Int)

// TESTCASE NUMBER: 1
fun box(): String {
    val (x, y) = Point(10, 20)
    return if (x == 10 && y == 20) "OK" else "NOK"
}
