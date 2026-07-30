// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: multiline string literal supports the same interpolation syntax
 */

// TESTCASE NUMBER: 1
fun test(v: Int): String = """line=${v}"""

fun box(): String {
    if (test(7) != "line=7") return "NOK"
    if (test(0) != "line=0") return "NOK"
    return "OK"
}
