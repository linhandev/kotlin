// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 1 -> sentence 1
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: when expression with enum subject is exhaustive when all enum constants are covered
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN }

fun test(c: Color): Int = when (c) {
    Color.RED -> 1
    Color.GREEN -> 2
}

fun box(): String {
    if (test(Color.RED) != 1) return "NOK"
    if (test(Color.GREEN) != 2) return "NOK"
    return "OK"
}
