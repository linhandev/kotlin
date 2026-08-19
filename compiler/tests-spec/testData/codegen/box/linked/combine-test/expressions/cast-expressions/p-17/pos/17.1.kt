// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 17 -> sentence 17
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: as? List<String> from List<*> is unchecked
 */

// TESTCASE NUMBER: 1
@Suppress("UNCHECKED_CAST")
fun test(a: List<*>): List<String>? = a as? List<String>

fun box(): String {
    val a = test(listOf("x"))
    if (a == null || a != listOf("x")) return "NOK"
    // erasure: as? List<String> typically succeeds for List<Int> too
    val b = test(listOf(1))
    if (b == null || b.size != 1) return "NOK"
    return "OK"
}
