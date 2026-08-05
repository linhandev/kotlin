// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 5 -> sentence 5
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: hex Int literal equals decimal value and toLong() matches hex Long literal
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 0xFF == 255 && 0xFF.toLong() == 0xFFL

fun box(): String {
    if (!test()) return "NOK: hex Int literal value and toLong equals hex Long literal"
    val hexInt: Int = 0xFF
    if (hexInt + 1 != 256) return "NOK: hex Int literal in Int arithmetic"
    val hexLong: Long = 0xFFL
    if (hexInt.toLong() != hexLong) return "NOK: hex Int literal converts to same value as hex Long literal"
    if (0xFF != 255) return "NOK: hex literal equals decimal equivalent"
    return "OK"
}
