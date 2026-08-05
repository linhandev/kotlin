// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 4 -> sentence 4
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Long literal with numeric separator is typed as Long and equivalent to plain Long literal
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 1_000L == 1000L && 1_000L + 2_000L == 3_000L

fun box(): String {
    if (!test()) return "NOK: separator Long equals plain Long and Long arithmetic"
    val x: Long = 1_000L
    if (x + 2_000L != 3_000L) return "NOK: Long separator literals in Long arithmetic"
    if (1_000L != 1000L) return "NOK: separator Long equals plain Long"
    return "OK"
}
