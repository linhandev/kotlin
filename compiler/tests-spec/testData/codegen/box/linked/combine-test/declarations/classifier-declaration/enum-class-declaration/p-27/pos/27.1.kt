// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 27 -> sentence 27
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: enum constant set is closed and known at compile time for exhaustiveness
 */

// TESTCASE NUMBER: 1
enum class Color { R, G }

fun test(c: Color): String = when (c) {
    Color.R -> "r"
    Color.G -> "g"
}

fun box(): String {
    if (test(Color.R) != "r") return "NOK"
    if (test(Color.G) != "g") return "NOK"
    return "OK"
}
