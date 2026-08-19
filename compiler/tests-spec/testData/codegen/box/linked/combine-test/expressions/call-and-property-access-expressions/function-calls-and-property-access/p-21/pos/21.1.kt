// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 21 -> sentence 21
 * PRIMARY LINKS: expressions, function-literals, lambda-literals -> paragraph 21 -> sentence 21
 *                type-system, nullable-types -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: safe call followed by trailing lambda transforms nullable receiver
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String = x?.let { it.uppercase() } ?: "none"

fun box(): String {
    if (test("ab") != "AB") return "NOK: value"
    if (test(null) != "none") return "NOK: null"
    return "OK"
}
