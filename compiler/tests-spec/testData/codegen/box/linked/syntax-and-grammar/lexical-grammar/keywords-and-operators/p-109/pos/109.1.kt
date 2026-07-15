// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 109 -> sentence 109
 * NUMBER: 1
 * DESCRIPTION: DATA token in data class declaration
 */
data class Point109(val x: Int, val y: Int)

// TESTCASE NUMBER: 1
fun box(): String {
    val point = Point109(40, 2)
    return if (point.x + point.y == 42) "OK" else "NOK"
}
