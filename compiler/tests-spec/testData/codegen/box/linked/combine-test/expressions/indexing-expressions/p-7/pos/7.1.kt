// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 7 -> sentence 7
 *                type-system, introduction-1 -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: Map index read yields nullable value
 */

// TESTCASE NUMBER: 1
fun test(): Int? = mapOf("a" to 1)["a"]

fun box(): String {
    if (test() != 1) return "NOK"
    if (mapOf("a" to 1)["missing"] != null) return "NOK"
    return "OK"
}
