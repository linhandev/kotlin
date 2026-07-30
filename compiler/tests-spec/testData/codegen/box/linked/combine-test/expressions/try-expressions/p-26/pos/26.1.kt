// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 26 -> sentence 26
 *                expressions, elvis-operator-expressions -> paragraph 26 -> sentence 26
 *                expressions, jump-expressions, return-expressions -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: finally return overrides try Elvis result while try body still runs
 */

// TESTCASE NUMBER: 1
var side = 0

fun test(x: String?): String {
    return try {
        side++
        x ?: "empty"
    } finally {
        return "finally"
    }
}

fun box(): String {
    side = 0
    if (test("hi") != "finally") return "NOK"
    if (side != 1) return "NOK"
    side = 0
    if (test(null) != "finally") return "NOK"
    if (side != 1) return "NOK"
    return "OK"
}
