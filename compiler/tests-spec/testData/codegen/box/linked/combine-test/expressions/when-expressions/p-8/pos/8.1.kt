// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 8 -> sentence 8
 *                type-system, introduction-1 -> paragraph 8 -> sentence 8
 *                expressions, when-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable enum subject and else branch covering null and remaining enum constants
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN }

fun test(c: Color?): Int = when (c) {
    Color.RED -> 1
    else -> 0
}

fun box(): String {
    if (test(Color.RED) != 1) return "NOK"
    if (test(Color.GREEN) != 0) return "NOK"
    if (test(null) != 0) return "NOK"
    return "OK"
}
