// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 2 -> sentence 2
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: as? returns null on mismatch or null
 */

// TESTCASE NUMBER: 1
fun test(x: Any?): String? = x as? String

fun box(): String {
    if (test("hi") != "hi") return "NOK"
    if (test(1) != null) return "NOK"
    if (test(null) != null) return "NOK"
    if ((null as? String) != null) return "NOK"
    val s: String? = null
    if ((s as? String) != null) return "NOK"
    return "OK"
}
