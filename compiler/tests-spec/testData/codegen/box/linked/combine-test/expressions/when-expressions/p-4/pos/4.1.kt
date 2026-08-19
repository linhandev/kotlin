// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 4 -> sentence 4
 *                expressions, when-expressions -> paragraph 4 -> sentence 4
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: when expression with multiple enum constants in one branch is still exhaustive
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }

fun test(c: Color): Int = when (c) {
    Color.RED, Color.GREEN -> 1
    Color.BLUE -> 2
}

fun box(): String {
    if (test(Color.RED) != 1) return "NOK"
    if (test(Color.GREEN) != 1) return "NOK"
    if (test(Color.BLUE) != 2) return "NOK"
    return "OK"
}
