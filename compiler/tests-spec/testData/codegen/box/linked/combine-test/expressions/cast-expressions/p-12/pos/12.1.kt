// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 12 -> sentence 12
 *                type-inference, smart-casts -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: redundant as after is
 */

// TESTCASE NUMBER: 1
@Suppress("USELESS_CAST")
fun test(x: Any): Int = if (x is String) (x as String).length else 0

fun box(): String {
    if (test("hi") != 2) return "NOK"
    if (test(1) != 0) return "NOK"
    return "OK"
}
