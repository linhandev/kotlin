// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 1 -> sentence 1
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: when expression on enum is exhaustive when all constants are covered
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }

fun test(c: Color): String = when (c) {
    Color.RED -> "r"
    Color.GREEN -> "g"
    Color.BLUE -> "b"
}

fun box(): String {
    if (test(Color.RED) != "r") return "NOK"
    if (test(Color.GREEN) != "g") return "NOK"
    if (test(Color.BLUE) != "b") return "NOK"
    return "OK"
}
