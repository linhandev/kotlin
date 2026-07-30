// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 10 -> sentence 10
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 10 -> sentence 10
 * NUMBER: 1
 * DESCRIPTION: extension function call can be used inside ${} interpolation
 */

// TESTCASE NUMBER: 1
fun String.tag(): String = "[$this]"

fun test(s: String): String = "t=${s.tag()}"

fun box(): String {
    if (test("hi") != "t=[hi]") return "NOK"
    if (test("") != "t=[]") return "NOK"
    return "OK"
}
