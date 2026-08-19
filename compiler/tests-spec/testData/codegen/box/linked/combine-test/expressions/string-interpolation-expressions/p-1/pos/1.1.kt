// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, string-interpolation-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: simple identifier interpolation resolves to local variable access
 */

// TESTCASE NUMBER: 1
fun test(name: String): String = "hello, $name"

fun box(): String {
    if (test("world") != "hello, world") return "NOK"
    if (test("") != "hello, ") return "NOK"
    return "OK"
}
