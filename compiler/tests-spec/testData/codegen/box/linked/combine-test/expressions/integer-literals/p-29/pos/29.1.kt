// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: syntax-and-grammar, lexical-grammar, literals -> paragraph 29 -> sentence 29
 *                expressions, range-expressions -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: containment check with separator integer literals in range bounds
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 1_500 in 1_000..2_000

fun box(): String {
    if (!test()) return "NOK: separator literal inside separator range"
    if (1_000 !in 1_000..2_000) return "NOK: separator lower bound inclusive"
    if (2_000 !in 1_000..2_000) return "NOK: separator upper bound inclusive"
    if (999 in 1_000..2_000) return "NOK: value below separator range"
    if (2_001 in 1_000..2_000) return "NOK: value above separator range"
    return "OK"
}
