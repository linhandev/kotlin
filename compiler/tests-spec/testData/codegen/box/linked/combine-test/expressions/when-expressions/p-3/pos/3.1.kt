// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 3 -> sentence 3
 *                expressions, when-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: when expression with enum subject and else branch covering remaining enum constants
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN, BLUE }

fun test(c: Color): Int = when (c) {
    Color.RED -> 1
    else -> 0
}

fun box(): String {
    if (test(Color.RED) != 1) return "NOK"
    if (test(Color.GREEN) != 0) return "NOK"
    if (test(Color.BLUE) != 0) return "NOK"
    return "OK"
}
