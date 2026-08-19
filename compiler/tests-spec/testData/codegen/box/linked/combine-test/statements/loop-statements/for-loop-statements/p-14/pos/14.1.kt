// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 14 -> sentence 14
 *                declarations, destructuring-declarations -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: for-in destructuring for ((k,v) in map)
 */

// TESTCASE NUMBER: 1
fun test(): String { val keys = mutableListOf<String>(); for ((k, _) in mapOf("x" to 1, "y" to 2)) keys += k; return keys.sorted().joinToString(",") }

fun box(): String {
    if (test() != "x,y") return "NOK"
    return "OK"
}
