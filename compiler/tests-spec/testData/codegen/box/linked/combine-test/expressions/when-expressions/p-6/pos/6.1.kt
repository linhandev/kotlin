// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 6 -> sentence 6
 *                type-system, introduction-1 -> paragraph 6 -> sentence 6
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: when expression with nullable enum subject is exhaustive when null branch is covered
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN }

fun test(c: Color?): Int = when (c) {
    Color.RED -> 1
    Color.GREEN -> 2
    null -> -1
}

fun box(): String {
    if (test(Color.RED) != 1) return "NOK"
    if (test(Color.GREEN) != 2) return "NOK"
    if (test(null) != -1) return "NOK"
    return "OK"
}
