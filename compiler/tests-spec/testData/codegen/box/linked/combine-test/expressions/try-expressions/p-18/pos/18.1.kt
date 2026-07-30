// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 18 -> sentence 18
 *                type-inference, introduction-1 -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: catch returning non-null still allows overall nullable type
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String? = try {
    x
} catch (e: Exception) {
    "recovered"
}

fun throwAndRecover(): String? = try {
    throw IllegalStateException()
} catch (e: Exception) {
    "recovered"
}

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    if (test(null) != null) return "NOK"
    if (throwAndRecover() != "recovered") return "NOK"
    return "OK"
}
