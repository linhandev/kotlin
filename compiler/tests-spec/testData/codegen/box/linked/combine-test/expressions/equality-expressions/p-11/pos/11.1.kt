// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 11 -> sentence 11
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 11 -> sentence 11
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 11 -> sentence 11
 * NUMBER: 1
 * DESCRIPTION: Int == drives if branch selecting arithmetic result
 */

// TESTCASE NUMBER: 1
fun test(a: Int, b: Int): Int = if (a == b) a + b else -1

fun box(): String {
    if (test(3, 3) != 6) return "NOK"
    if (test(3, 4) != -1) return "NOK"
    if (test(0, 0) != 0) return "NOK"
    return "OK"
}
