// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: underscore skips a destructuring component in a lambda
 */

// TESTCASE NUMBER: 1
fun test(ps: List<Pair<Int, String>>): List<Int> = ps.map { (k, _) -> k }

fun box(): String {
    if (test(listOf(1 to "a", 2 to "b")) != listOf(1, 2)) return "NOK"
    return "OK"
}
