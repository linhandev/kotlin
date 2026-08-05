// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: 空 Iterable 循环体不执行
 */

// TESTCASE NUMBER: 1
fun test(): Int { var n = 0; for (x in emptyList<Int>()) n++; return n }

fun box(): String {
    if (test() != 0) return "NOK"
    return "OK"
}
