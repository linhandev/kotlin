// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 9 -> sentence 9
 *                statements, assignments, simple-assignments -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: String index read via get
 */

// TESTCASE NUMBER: 1
fun test(): Char = "ab"[0]

fun box(): String {
    if (test() != 'a') return "NOK"
    return "OK"
}
