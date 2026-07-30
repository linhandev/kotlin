// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: unchecked as List<String> from Any
 */

// TESTCASE NUMBER: 1
@Suppress("UNCHECKED_CAST")
fun test(a: Any): List<String> = a as List<String>

fun box(): String {
    val ok = test(listOf("a", "b"))
    if (ok != listOf("a", "b")) return "NOK"
    val sneaky = test(listOf(1, 2))
    if (sneaky.size != 2) return "NOK"
    return "OK"
}
