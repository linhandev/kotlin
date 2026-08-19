// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 9 -> sentence 9
 *                expressions, when-expressions -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: when expression with Any subject does not require enum exhaustiveness when else branch is present
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN }

fun test(x: Any): Int = when (x) {
    Color.RED -> 1
    Color.GREEN -> 2
    else -> 0
}

fun box(): String {
    if (test(Color.RED) != 1) return "NOK"
    if (test(Color.GREEN) != 2) return "NOK"
    if (test("other") != 0) return "NOK"
    return "OK"
}
