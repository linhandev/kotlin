// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 107 -> sentence 107
 * NUMBER: 3
 * DESCRIPTION: SEALED token in sealed class with object subclasses
 */
// TESTCASE NUMBER: 1
sealed class Shape107 {
    object Circle107 : Shape107()
    object Square107 : Shape107()
}

fun label107(shape: Shape107): String = when (shape) {
    Shape107.Circle107 -> "OK"
    Shape107.Square107 -> "NOK"
}

fun box(): String = label107(Shape107.Circle107)
