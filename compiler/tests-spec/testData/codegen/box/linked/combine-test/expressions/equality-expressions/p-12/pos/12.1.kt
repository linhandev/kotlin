// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 12 -> sentence 12
 *                type-inference, smart-casts -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: Int? == Int then smart cast uses n as Int
 */

// TESTCASE NUMBER: 1
fun test(n: Int?): Int = if (n == 1) n + 10 else -1

fun box(): String {
    if (test(null) != -1) return "NOK"
    if (test(1) != 11) return "NOK"
    if (test(2) != -1) return "NOK"
    return "OK"
}
