/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 44 -> sentence 44
 * PRIMARY LINKS: expressions, jump-expressions, return-expressions -> paragraph 44 -> sentence 44
 * NUMBER: 1
 * DESCRIPTION: anonymous function return exits only the anonymous function
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val f = fun(): Int { return 2 }
    f()
    return 1
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
