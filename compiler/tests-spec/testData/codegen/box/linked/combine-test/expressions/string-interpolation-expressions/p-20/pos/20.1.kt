// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: escaped dollar sign is not treated as interpolation
 */

// TESTCASE NUMBER: 1
fun test(): String = "\$not interpolated"

fun box(): String {
    if (test() != "\$not interpolated") return "NOK"
    return "OK"
}
