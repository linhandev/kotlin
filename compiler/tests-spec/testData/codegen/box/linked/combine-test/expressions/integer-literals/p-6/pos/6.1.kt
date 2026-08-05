// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 6 -> sentence 6
 *                expressions, multiplicative-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: hex Long literal equals hex Int literal toLong() in Long multiplication
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 0x10L == 0x10.toLong() && 0x10L * 2L == 32L

fun box(): String {
    if (!test()) return "NOK: hex Long literal equals Int toLong and Long multiplication"
    val x: Long = 0x10L
    if (x * 2L != 32L) return "NOK: hex Long literal in Long arithmetic"
    if (0x10.toLong() != x) return "NOK: same numeric value as unsuffixed hex Int literal"
    return "OK"
}
