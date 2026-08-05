// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Set 可 for-in 遍历
 */

// TESTCASE NUMBER: 1
fun test(): Int { var n = 0; for (x in setOf(1, 2)) n++; return n }

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
