// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 13 -> sentence 13
 *                type-inference, introduction-1 -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: try expression with all Nothing branches is usable at expected type Int
 */

// TESTCASE NUMBER: 1
fun test(): Int = try {
    throw Exception("boom")
} catch (e: Exception) {
    throw e
}

fun box(): String {
    try {
        test()
        return "NOK"
    } catch (e: Exception) {
        if (e.message != "boom") return "NOK"
        return "OK"
    }
}
