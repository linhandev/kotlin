// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 28 -> sentence 28
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 28 -> sentence 28
 *                type-system, introduction-1 -> paragraph 28 -> sentence 28
 * NUMBER: 1
 * DESCRIPTION: 可空元素 Iterable<Int?> 可 for-in
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in listOf(null, 2)) s += (x ?: 0); return s }

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
