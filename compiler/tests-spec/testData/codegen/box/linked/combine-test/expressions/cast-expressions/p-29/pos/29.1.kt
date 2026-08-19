// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 29 -> sentence 29
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: is List star then as? List String
 */

// TESTCASE NUMBER: 1
@Suppress("UNCHECKED_CAST")
fun test(x: Any): List<String>? = if (x is List<*>) x as? List<String> else null

fun box(): String {
    if (test("no") != null) return "NOK"
    val a = test(listOf("x"))
    if (a == null || a != listOf("x")) return "NOK"
    val b = test(listOf(1))
    if (b == null || b.size != 1) return "NOK"
    return "OK"
}
