// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: function type value invoked and interpolated inside ${}
 */

// TESTCASE NUMBER: 1
fun test(f: () -> Int): String = "v=${f()}"

fun box(): String {
    if (test { 7 } != "v=7") return "NOK"
    if (test { 0 } != "v=0") return "NOK"
    return "OK"
}
