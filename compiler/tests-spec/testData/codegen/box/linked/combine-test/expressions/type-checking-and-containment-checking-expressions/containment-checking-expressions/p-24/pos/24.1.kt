// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, type-checking-and-containment-checking-expressions, containment-checking-expressions -> paragraph 24 -> sentence 24
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 24 -> sentence 24
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 24 -> sentence 24
 * NUMBER: 1
 * DESCRIPTION: in operator on Map checks key membership via containsKey convention at runtime
 */

// TESTCASE NUMBER: 1
fun test(m: Map<String, Int>, key: String): Boolean = key in m

fun box(): String {
    val m = mapOf("a" to 1)
    if (!test(m, "a")) return "NOK: key present"
    if (test(m, "b")) return "NOK: key absent"
    if (test(m, "a") != m.containsKey("a")) return "NOK: in not equivalent to containsKey for present key"
    if (test(m, "b") != m.containsKey("b")) return "NOK: in not equivalent to containsKey for absent key"
    return "OK"
}
