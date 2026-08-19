// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: zero-argument call expression can be used inside ${} interpolation
 */

// TESTCASE NUMBER: 1
fun getName(): String = "Alice"

fun test(): String = "name=${getName()}"

fun box(): String {
    if (test() != "name=Alice") return "NOK"
    return "OK"
}
