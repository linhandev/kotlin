// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 3 -> sentence 3
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: else branch makes when on enum usable as an expression
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }

fun test(c: Color): String = when (c) {
    Color.RED -> "r"
    else -> "other"
}

fun box(): String {
    if (test(Color.RED) != "r") return "NOK"
    if (test(Color.GREEN) != "other") return "NOK"
    return "OK"
}
