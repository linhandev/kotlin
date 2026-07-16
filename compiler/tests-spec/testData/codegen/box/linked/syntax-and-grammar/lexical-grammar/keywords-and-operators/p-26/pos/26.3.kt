// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 26 -> sentence 26
 * NUMBER: 3
 * DESCRIPTION: MULT_ASSIGNMENT token used with custom timesAssign operator
 */
// TESTCASE NUMBER: 1

class Scale(var factor: Int) {
    operator fun timesAssign(multiplier: Int) {
        factor *= multiplier
    }
}

fun box(): String {
    val scale = Scale(3)
    scale *= 4
    return if (scale.factor == 12) "OK" else "NOK"
}
