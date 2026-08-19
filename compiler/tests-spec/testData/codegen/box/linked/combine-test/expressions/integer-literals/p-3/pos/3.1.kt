// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: syntax-and-grammar, lexical-grammar, literals -> paragraph 3 -> sentence 3
 *                expressions, equality-expressions -> paragraph 3 -> sentence 3
 *                expressions, additive-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: numeric separator in decimal integer literal is equivalent to plain literal in equality and arithmetic
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 1_000 == 1000 && 1_000 + 2_000 == 3_000

fun box(): String {
    if (!test()) return "NOK: separator literal equals plain literal"
    if (1_000 + 2_000 != 3_000) return "NOK: separator literals in Int arithmetic"
    if (10_00 != 1000) return "NOK: separator at different position"
    if (1_000 != 999 + 1) return "NOK: separator literal in expression context"
    return "OK"
}
