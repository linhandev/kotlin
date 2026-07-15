// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 10 -> sentence 10
 * NUMBER: 2
 * DESCRIPTION: MULT token used in operator overloading a.times(b)
 */

data class Vec(val x: Int, val y: Int) {
    operator fun times(scalar: Int): Vec = Vec(x * scalar, y * scalar)
}

// TESTCASE NUMBER: 1
fun box(): String {
    val v = Vec(2, 3) * 5
    return if (v.x == 10 && v.y == 15) "OK" else "NOK"
}
