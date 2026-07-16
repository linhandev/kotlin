// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 3 -> sentence 3
 * NUMBER: 4
 * DESCRIPTION: COMMA token used as separator in enum entries
 */
// TESTCASE NUMBER: 1

enum class Color { RED, GREEN, BLUE }

fun box(): String {
    val colors = Color.entries
    return if (colors.size == 3 && colors[0] == Color.RED && colors[2] == Color.BLUE) "OK" else "NOK"
}
