// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: interpolated value is converted to String via toString()
 */

// TESTCASE NUMBER: 1
fun test(n: Int): String = "n=$n"

fun box(): String {
    if (test(1) != "n=1") return "NOK"
    if (test(0) != "n=0") return "NOK"
    if (test(-42) != "n=-42") return "NOK"
    return "OK"
}
