// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, elvis-operator-expressions -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: expressions, jump-expressions, throw-expressions -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: Elvis with non-nullable left and throw RHS does not execute throw
 */

// TESTCASE NUMBER: 1
@Suppress("USELESS_ELVIS")
fun test(x: String): Int = x.length ?: throw IllegalArgumentException()

fun box(): String {
    if (test("hi") != 2) return "NOK"
    return "OK"
}
