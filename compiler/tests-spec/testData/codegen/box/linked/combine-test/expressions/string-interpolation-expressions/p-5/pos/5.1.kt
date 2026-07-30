// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: call expression with arguments can be used inside ${} interpolation
 */

// TESTCASE NUMBER: 1
fun sum(a: Int, b: Int): Int = a + b

fun test(): String = "sum=${sum(1, 2)}"

fun box(): String {
    if (test() != "sum=3") return "NOK"
    if ("sum=${sum(10, 20)}" != "sum=30") return "NOK"
    return "OK"
}
