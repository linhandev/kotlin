// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 19 -> sentence 19
 *                expressions, multiplicative-expressions -> paragraph 19 -> sentence 19
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: Long separator literals in multiplicative expression evaluate to 2000L
 */

// TESTCASE NUMBER: 1
fun test(): Long = 10_00L * 2L

fun box(): String {
    if (test() != 2000L) return "NOK: separator Long literals multiplication result"
    if (10_00L * 2L != 2_000L) return "NOK: equivalent separator Long literal product"
    if (1_000L * 3L != 3_000L) return "NOK: another separator Long literal pair"
    if (10_00L / 2L != 500L) return "NOK: separator Long literals in division context"
    return "OK"
}
