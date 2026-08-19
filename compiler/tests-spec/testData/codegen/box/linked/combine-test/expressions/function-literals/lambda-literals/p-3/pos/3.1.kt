// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, function-literals, lambda-literals -> paragraph 3 -> sentence 3
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 3 -> sentence 3
 * NUMBER: 1
 * DESCRIPTION: lambda destructuring parameters may have explicit types
 */

// TESTCASE NUMBER: 1
fun test(): List<Int> = listOf(1 to "a").map { (i: Int, s: String) -> i + s.length }

fun box(): String {
    if (test() != listOf(2)) return "NOK"
    return "OK"
}
