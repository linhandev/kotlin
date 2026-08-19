// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 2 -> sentence 2
 *                expressions, multiplicative-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: L suffix Long literal equals Int literal toLong() in Long multiplication
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 42L == 42.toLong() && 42L * 2L == 84L

fun box(): String {
    if (!test()) return "NOK: Long literal equals Int toLong and Long multiplication"
    val x: Long = 42L
    if (x * 2L != 84L) return "NOK: Long literal in Long arithmetic"
    if (42.toLong() != x) return "NOK: same numeric value as unsuffixed Int literal"
    return "OK"
}
