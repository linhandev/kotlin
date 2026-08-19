// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, enum-class-declaration -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, when-expressions -> paragraph 4 -> sentence 4
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: when used as a statement with else covers remaining enum constants
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }

fun test(c: Color): String {
    var out = "other"
    when (c) {
        Color.RED -> out = "r"
        else -> out = "other"
    }
    return out
}

fun box(): String {
    if (test(Color.RED) != "r") return "NOK"
    if (test(Color.BLUE) != "other") return "NOK"
    return "OK"
}
