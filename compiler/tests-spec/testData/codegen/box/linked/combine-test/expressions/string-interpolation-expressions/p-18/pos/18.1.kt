// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: adjacent interpolations and literal text concatenate in one template
 */

// TESTCASE NUMBER: 1
fun test(a: Int, b: Int): String = "$a+$b=${a + b}"

fun box(): String {
    if (test(1, 2) != "1+2=3") return "NOK"
    if (test(10, 5) != "10+5=15") return "NOK"
    return "OK"
}
