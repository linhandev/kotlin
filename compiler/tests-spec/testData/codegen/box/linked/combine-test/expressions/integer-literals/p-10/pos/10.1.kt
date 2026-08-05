// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 10 -> sentence 10
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 10 -> sentence 10
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: separator literal beyond Int.MAX_VALUE is Long and Int.MAX_VALUE boundary remains Int
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 2_147_483_648 is Long && 2_147_483_647 == Int.MAX_VALUE

fun box(): String {
    if (!test()) return "NOK: beyond Int max is Long and Int.MAX_VALUE boundary is Int"
    val beyondInt: Long = 2_147_483_648
    if (beyondInt - 1L != Int.MAX_VALUE.toLong()) return "NOK: one less is Int.MAX_VALUE as Long"
    if (2_147_483_647 != Int.MAX_VALUE) return "NOK: Int.MAX_VALUE boundary still Int"
    if (beyondInt + 1L != 2_147_483_649L) return "NOK: Long arithmetic beyond Int range"
    return "OK"
}
