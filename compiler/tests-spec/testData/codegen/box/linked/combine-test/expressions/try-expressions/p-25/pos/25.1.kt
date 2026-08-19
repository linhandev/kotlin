// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 25 -> sentence 25
 *                expressions, elvis-operator-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: finally does not change nullable Elvis result type of try expression
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String = try {
    x ?: "empty"
} catch (e: Exception) {
    "error"
} finally {
    println("done")
}

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    if (test(null) != "empty") return "NOK"
    return "OK"
}
