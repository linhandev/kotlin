// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 4 -> sentence 4
 *                operator-overloading, overview -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: IntRange can be used as for-in subject
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (i in 1..3) s += i; return s }

fun box(): String {
    if (test() != 6) return "NOK"
    return "OK"
}
