// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 6 -> sentence 6
 *                expressions, elvis-operator-expressions -> paragraph 6 -> sentence 6
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: as? combined with Elvis
 */

// TESTCASE NUMBER: 1
fun test(x: Any): String = (x as? String) ?: "default"

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    if (test(1) != "default") return "NOK"
    return "OK"
}
