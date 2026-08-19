// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 4 -> sentence 4
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: nullable as non-null throws when value is null
 */

// TESTCASE NUMBER: 1
fun test(x: String?): String = x as String

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    try {
        test(null)
        return "NOK"
    } catch (_: ClassCastException) {
    } catch (_: NullPointerException) {
    }
    return "OK"
}
