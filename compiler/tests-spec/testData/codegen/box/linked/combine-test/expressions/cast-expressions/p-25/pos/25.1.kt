// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, cast-expressions -> paragraph 25 -> sentence 25
 * PRIMARY LINKS: expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 25 -> sentence 25
 * NUMBER: 1
 * DESCRIPTION: filterIsInstance yields List without handwritten as
 */

// TESTCASE NUMBER: 1
fun test(xs: List<Any>): List<String> = xs.filterIsInstance<String>()

fun box(): String {
    val r = test(listOf("a", 1, "b"))
    if (r != listOf("a", "b")) return "NOK"
    return "OK"
}
