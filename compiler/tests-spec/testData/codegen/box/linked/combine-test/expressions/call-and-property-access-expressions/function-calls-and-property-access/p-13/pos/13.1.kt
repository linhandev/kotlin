// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 13 -> sentence 13
 *                type-inference, function-signature-type-inference, statements-with-lambda-literals -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: trailing lambda parameter types are inferred from the call context
 */

// TESTCASE NUMBER: 1
fun test(xs: List<String>): List<String> = xs.map { it.uppercase() }

fun box(): String {
    if (test(listOf("a", "bc")) != listOf("A", "BC")) return "NOK"
    if (test(emptyList<String>()) != emptyList<String>()) return "NOK"
    return "OK"
}
