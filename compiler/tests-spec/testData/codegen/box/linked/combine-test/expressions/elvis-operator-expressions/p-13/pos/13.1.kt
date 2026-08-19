// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: Elvis with non-nullable literal left and return RHS is dead code
 */

// TESTCASE NUMBER: 1
@Suppress("USELESS_ELVIS")
fun test(): Int {
    return 1 ?: return 0
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
