// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Array index read via get
 */

// TESTCASE NUMBER: 1
fun test(): Int = arrayOf(10, 20)[0]

fun box(): String {
    if (test() != 10) return "NOK"
    return "OK"
}
