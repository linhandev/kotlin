// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: nested string template inside ${} is evaluated before outer concatenation
 */

// TESTCASE NUMBER: 1
fun test(x: Int): String = "r=${"${x}"}"

fun box(): String {
    if (test(5) != "r=5") return "NOK"
    if (test(0) != "r=0") return "NOK"
    return "OK"
}
