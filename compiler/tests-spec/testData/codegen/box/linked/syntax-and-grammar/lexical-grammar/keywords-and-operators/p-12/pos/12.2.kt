// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 12 -> sentence 12
 * NUMBER: 2
 * DESCRIPTION: DIV token used in operator overloading a.div(b)
 */

data class Fraction(val num: Int, val den: Int) {
    operator fun div(other: Fraction): Fraction = Fraction(num * other.den, den * other.num)
}

// TESTCASE NUMBER: 1
fun box(): String {
    val a = Fraction(1, 2)
    val b = Fraction(1, 4)
    val c = a / b
    return if (c.num == 4 && c.den == 2) "OK" else "NOK"
}
