// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: List 上 for-in 累加元素
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in listOf(1, 2, 3)) s += x; return s }

fun box(): String {
    if (test() != 6) return "NOK"
    return "OK"
}
