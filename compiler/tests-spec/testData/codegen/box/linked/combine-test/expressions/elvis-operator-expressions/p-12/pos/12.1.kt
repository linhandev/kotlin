// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: Elvis with non-nullable literal left and throw RHS is dead code
 */

// TESTCASE NUMBER: 1
@Suppress("USELESS_ELVIS")
fun test(): Int = 1 ?: throw Exception()

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
