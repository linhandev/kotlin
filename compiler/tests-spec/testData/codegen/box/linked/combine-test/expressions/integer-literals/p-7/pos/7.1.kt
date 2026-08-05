// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: syntax-and-grammar, lexical-grammar, literals -> paragraph 7 -> sentence 7
 *                expressions, equality-expressions -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: numeric separator in hexadecimal integer literal is equivalent to plain hex literal in equality and arithmetic
 */

// TESTCASE NUMBER: 1
fun test(): Int = 0x1_0

fun box(): String {
    if (test() != 16) return "NOK: hex separator literal value"
    if (0x1_0 != 0x10) return "NOK: hex separator equals plain hex literal"
    if (0x1_0 + 0x1_0 != 0x2_0) return "NOK: hex separator literals in Int arithmetic"
    if (0xF_F != 255) return "NOK: hex separator at different position"
    return "OK"
}
