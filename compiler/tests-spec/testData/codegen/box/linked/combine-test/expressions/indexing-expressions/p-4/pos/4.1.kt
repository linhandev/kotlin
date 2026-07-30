// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 4 -> sentence 4
 *                statements, assignments, simple-assignments -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: readonly List index read via get
 */

// TESTCASE NUMBER: 1
fun test(): Int = listOf(1, 2)[1]

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
