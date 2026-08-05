// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: Array 可 for-in
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in arrayOf(1, 2, 3)) s += x; return s }

fun box(): String {
    if (test() != 6) return "NOK"
    return "OK"
}
