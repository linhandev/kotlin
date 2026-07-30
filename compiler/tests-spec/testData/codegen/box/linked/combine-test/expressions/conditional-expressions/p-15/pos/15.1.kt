// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, conditional-expressions -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 14 -> sentence 14
 *                expressions, cast-expressions -> paragraph 14 -> sentence 14
 *                type-system, introduction-1 -> paragraph 14 -> sentence 14
 *                expressions, elvis-operator-expressions -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: is CharSequence then as? String with Elvis (safe cast not redundant)
 */

// TESTCASE NUMBER: 1
fun test(x: Any): Int = if (x is CharSequence) (x as? String)?.length ?: -1 else -1

fun box(): String {
    if (test("hello") != 5) return "NOK"
    if (test("") != 0) return "NOK"
    if (test(StringBuilder("ab")) != -1) return "NOK"
    if (test(123) != -1) return "NOK"
    return "OK"
}
