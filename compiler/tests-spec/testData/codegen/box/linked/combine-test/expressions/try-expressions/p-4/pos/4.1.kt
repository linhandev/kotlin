// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 4 -> sentence 4
 *                type-inference, introduction-1 -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: throw branch is Nothing and does not affect try expression result type Int
 */

// TESTCASE NUMBER: 1
fun test(flag: Boolean): Int = try {
    if (flag) 1 else throw Exception()
} catch (e: Exception) {
    2
}

fun box(): String {
    if (test(true) != 1) return "NOK"
    if (test(false) != 2) return "NOK"
    return "OK"
}
