// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 20 -> sentence 20
 *                expressions, when-expressions -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: when null branch matches == null
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String = when (x) {
    null -> "nil"
    else -> x
}

fun box(): String {
    if (test(null) != "nil") return "NOK"
    if (test("a") != "a") return "NOK"
    return "OK"
}
