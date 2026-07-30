// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 8 -> sentence 8
 *                statements, assignments, simple-assignments -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: MutableMap index assign then read
 */

// TESTCASE NUMBER: 1
fun test(): Int {
    val m = mutableMapOf<String, Int>()
    m["k"] = 7
    return m["k"]!!
}

fun box(): String {
    if (test() != 7) return "NOK"
    return "OK"
}
