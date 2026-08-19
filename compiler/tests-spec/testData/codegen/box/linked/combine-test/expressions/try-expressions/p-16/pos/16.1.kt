// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: try block returning nullable value yields nullable overall type
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String? = try {
    x
} catch (e: Exception) {
    null
}

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    if (test(null) != null) return "NOK"
    return "OK"
}
