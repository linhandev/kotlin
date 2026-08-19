// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 1 -> sentence 1
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: as casts Any to String
 */

// TESTCASE NUMBER: 1
fun test(x: Any): String = x as String

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    try {
        test(1)
        return "NOK"
    } catch (_: ClassCastException) {
    }
    return "OK"
}
