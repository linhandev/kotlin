// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 26 -> sentence 26
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: negative Long literal with L suffix preserves value in arithmetic
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = -1L + 1L == 0L && -1_000L == -1000L

fun box(): String {
    if (!test()) return "NOK: negative Long literal addition and separator Long equality"
    if (-1L + 1L != 0L) return "NOK: negative Long literal in addition"
    if (-1_000L != -1000L) return "NOK: negative separator Long literal"
    if (-1L * 5L != -5L) return "NOK: negative Long literal in multiplication"
    return "OK"
}
