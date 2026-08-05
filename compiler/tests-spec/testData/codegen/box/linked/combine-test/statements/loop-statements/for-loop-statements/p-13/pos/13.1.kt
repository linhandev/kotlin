// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 13 -> sentence 13
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 13 -> sentence 13
 * NUMBER: 1
 * DESCRIPTION: Map 默认 for-in 遍历 Entry
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (e in mapOf("a" to 1, "b" to 2)) s += e.value; return s }

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
