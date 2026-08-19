// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 20 -> sentence 20
 *                expressions, elvis-operator-expressions -> paragraph 20 -> sentence 20
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: catch uses Elvis on nullable exception message
 */

// TESTCASE NUMBER: 1
fun test(): String = try {
    error("bad")
} catch (e: Exception) {
    e.message ?: "unknown"
}

fun testNullMessage(): String = try {
    throw Exception()
} catch (e: Exception) {
    e.message ?: "unknown"
}

fun box(): String {
    if (test() != "bad") return "NOK"
    if (testNullMessage() != "unknown") return "NOK"
    return "OK"
}
