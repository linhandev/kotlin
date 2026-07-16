// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 27 -> sentence 27
 * NUMBER: 3
 * DESCRIPTION: DIV_ASSIGNMENT token used with custom divAssign operator
 */
// TESTCASE NUMBER: 1

class Ratio(var numerator: Int, var denominator: Int) {
    operator fun divAssign(value: Int) {
        numerator /= value
        denominator /= value
    }
}

fun box(): String {
    val ratio = Ratio(12, 24)
    ratio /= 3
    return if (ratio.numerator == 4 && ratio.denominator == 8) "OK" else "NOK"
}
