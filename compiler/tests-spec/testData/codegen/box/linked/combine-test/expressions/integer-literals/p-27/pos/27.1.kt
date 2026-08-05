// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 27 -> sentence 27
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: negative separator Int literal in Int arithmetic and toLong() matches Long literal
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = -1_000 + 1_000 == 0 && (-1_000).toLong() == -1_000L

fun box(): String {
    if (!test()) return "NOK: negative separator Int arithmetic and toLong equals Long literal"
    if (-1_000 + 1_000 != 0) return "NOK: negative separator Int literal in addition"
    if (-1_000 != -(1_000)) return "NOK: unary minus on separator Int literal"
    if (-2_000 != -2000) return "NOK: another negative separator Int literal"
    return "OK"
}
