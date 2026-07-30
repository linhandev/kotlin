// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, try-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: built-in-types-and-their-semantics, kotlin.nothing -> paragraph 8 -> sentence 8
 *                expressions, jump-expressions, return-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: return in finally overrides try expression result
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    return try {
        1
    } finally {
        return 2
    }
}

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
