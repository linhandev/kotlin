// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: index reads compose in larger expression
 */

// TESTCASE NUMBER: 1
fun test(): Int = arrayOf(2, 3)[0] + arrayOf(1)[0]

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
