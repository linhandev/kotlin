// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: 可直接对 Iterator 实例 for-in
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in listOf(1, 2).iterator()) s += x; return s }

fun box(): String {
    if (test() != 3) return "NOK"
    return "OK"
}
