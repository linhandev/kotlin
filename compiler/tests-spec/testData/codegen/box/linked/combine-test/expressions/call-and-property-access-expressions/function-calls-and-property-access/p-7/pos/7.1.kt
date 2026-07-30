// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 7 -> sentence 7
 *                overload-resolution, building-the-overload-candidate-set, call-with-trailing-lambda-expressions -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: extension receiver call can use trailing lambda
 */

// TESTCASE NUMBER: 1
fun test(s: String): String = s.run { replace("a", "b") }

fun box(): String {
    if (test("aba") != "bbb") return "NOK"
    if (test("xyz") != "xyz") return "NOK"
    return "OK"
}
