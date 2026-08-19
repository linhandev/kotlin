// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 8 -> sentence 8
 *                type-inference, smart-casts -> paragraph 8 -> sentence 8
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: conditional expression with is smart cast and member function call in branch
 */

// TESTCASE NUMBER: 1
fun test(x: Any): String = if (x is String) x.uppercase() else ""

fun box(): String {
    if (test("hello") != "HELLO") return "NOK"
    if (test("Ab") != "AB") return "NOK"
    if (test(123) != "") return "NOK"
    return "OK"
}
