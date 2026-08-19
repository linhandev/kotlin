// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 7 -> sentence 7
 *                type-inference, smart-casts -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: == null else branch smart cast
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String = if (x == null) "nil" else x.uppercase()

fun box(): String {
    if (test(null) != "nil") return "NOK"
    if (test("ab") != "AB") return "NOK"
    return "OK"
}
