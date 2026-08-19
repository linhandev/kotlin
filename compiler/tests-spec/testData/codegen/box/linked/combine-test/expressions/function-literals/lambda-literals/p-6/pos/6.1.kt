// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: Map.Entry can be destructured in a lambda
 */

// TESTCASE NUMBER: 1
fun test(m: Map<String, Int>): Int = m.entries.sumOf { (k, v) -> k.length + v }

fun box(): String {
    if (test(mapOf("ab" to 1, "c" to 2)) != 6) return "NOK"
    return "OK"
}
