// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 9 -> sentence 9
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: Long binary literal with numeric separator is typed as Long and equivalent to plain binary Long literal
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 0b1_0_1_0L == 10L && 0b1_0_1_0L + 0b1_0_1_0L == 20L

fun box(): String {
    if (!test()) return "NOK: binary Long separator equals plain value and Long addition"
    val x: Long = 0b1_0_1_0L
    if (x + 0b1L != 0b1_0_1_1L) return "NOK: binary Long separator literals in Long arithmetic"
    if (0b1_0_1_0L != 0b1010L) return "NOK: separator binary Long equals plain binary Long"
    if (0b1_0_1_0.toLong() != x) return "NOK: same value as unsuffixed binary Int literal"
    return "OK"
}
