// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 5 -> sentence 5
 *                type-inference, introduction-1 -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: catch that throws is Nothing so overall type is determined by try block
 */

// TESTCASE NUMBER: 1
fun test(): Int = try {
    1
} catch (e: Exception) {
    throw e
}

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
