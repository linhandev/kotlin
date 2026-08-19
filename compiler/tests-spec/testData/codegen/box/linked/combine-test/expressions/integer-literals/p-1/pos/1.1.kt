// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 1 -> sentence 1
 *                expressions, additive-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: decimal Int literal toLong() equals Long literal and preserves Int arithmetic
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 42.toLong() == 42L && 42 + 1 == 43

fun box(): String {
    if (!test()) return "NOK: Int literal toLong equals Long literal and Int arithmetic"
    val intLit: Int = 42
    if (intLit + 1 != 43) return "NOK: Int literal in Int arithmetic"
    val longLit: Long = 42L
    if (intLit.toLong() != longLit) return "NOK: Int literal converts to same value as Long literal"
    if (2_147_483_647 != Int.MAX_VALUE) return "NOK: Int-range decimal literal without suffix"
    return "OK"
}
