// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, equality-expressions -> paragraph 23 -> sentence 23
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 23 -> sentence 23
 * NUMBER: 1
 * DESCRIPTION: Float? equals Float
 */

// TESTCASE NUMBER: 1
fun test(f: Float?): Boolean = f == 1.0f

fun box(): String {
    if (test(null)) return "NOK"
    if (!test(1.0f)) return "NOK"
    return "OK"
}
