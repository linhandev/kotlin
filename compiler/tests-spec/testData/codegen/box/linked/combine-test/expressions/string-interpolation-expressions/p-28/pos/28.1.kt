// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: interpolated string result remains String and can be passed to another function
 */

// TESTCASE NUMBER: 1
fun wrap(msg: String): String = msg

fun test(n: Int): String = wrap("n=$n")

fun box(): String {
    if (test(1) != "n=1") return "NOK"
    if (test(42) != "n=42") return "NOK"
    return "OK"
}
