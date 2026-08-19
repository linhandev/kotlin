// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: Elvis with non-nullable left and return RHS does not execute return
 */

// TESTCASE NUMBER: 1
@Suppress("USELESS_ELVIS")
fun test(x: String): Int {
    return x.length ?: return -1
}

fun box(): String {
    if (test("hi") != 2) return "NOK"
    return "OK"
}
