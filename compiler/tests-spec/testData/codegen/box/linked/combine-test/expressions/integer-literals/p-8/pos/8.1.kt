// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 8 -> sentence 8
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: binary Int literal equals decimal value and toLong() matches binary Long literal
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 0b1010 == 10 && 0b1010.toLong() == 0b1010L

fun box(): String {
    if (!test()) return "NOK: binary Int literal value and toLong equals binary Long literal"
    val binInt: Int = 0b1010
    if (binInt + 0b1 != 11) return "NOK: binary Int literal in Int arithmetic"
    val binLong: Long = 0b1010L
    if (binInt.toLong() != binLong) return "NOK: binary Int literal converts to same value as binary Long literal"
    if (0b1010 != 10) return "NOK: binary literal equals decimal equivalent"
    return "OK"
}
