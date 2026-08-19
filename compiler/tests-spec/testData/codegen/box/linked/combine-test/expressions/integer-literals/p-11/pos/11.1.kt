// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, integer-literals -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-system, built-in-integer-types -> paragraph 11 -> sentence 11
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 11 -> sentence 11
 *                syntax-and-grammar, lexical-grammar, literals -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: Int.MAX_VALUE separator literal is Int and toLong()+1 enters Long range
 */

// TESTCASE NUMBER: 1
fun test(): Boolean = 2_147_483_647 == Int.MAX_VALUE && 2_147_483_647.toLong() + 1L == 2_147_483_648L

fun box(): String {
    if (!test()) return "NOK: Int.MAX_VALUE is Int and toLong()+1 is in Long range"
    val atMax: Int = 2_147_483_647
    if (atMax != 2147483647) return "NOK: separator form equals undecorated Int.MAX_VALUE"
    if (atMax.toLong() + 1L != 2_147_483_648L) return "NOK: one past Int.MAX_VALUE is in Long range"
    if (atMax - 1 != Int.MAX_VALUE - 1) return "NOK: Int.MAX_VALUE in Int arithmetic"
    return "OK"
}
