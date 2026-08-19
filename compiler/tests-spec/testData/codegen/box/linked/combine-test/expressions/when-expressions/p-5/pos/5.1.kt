// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, when-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: declarations, classifier-declaration, enum-class-declaration -> paragraph 5 -> sentence 5
 *                expressions, when-expressions -> paragraph 5 -> sentence 5
 *                expressions, when-expressions, exhaustive-when-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: when expression with function call subject checks exhaustiveness on returned enum type
 */

// TESTCASE NUMBER: 1
enum class Color { RED, GREEN }

var pickedColor: Color = Color.RED

fun pick(): Color = pickedColor

fun test(): Int = when (pick()) {
    Color.RED -> 1
    Color.GREEN -> 2
}

fun box(): String {
    pickedColor = Color.RED
    if (test() != 1) return "NOK"
    pickedColor = Color.GREEN
    if (test() != 2) return "NOK"
    return "OK"
}
