// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: custom class implementing Iterable can be used in for-in
 */

// TESTCASE NUMBER: 1
class MyList(private val data: List<Int>) : Iterable<Int> { override fun iterator(): Iterator<Int> = data.iterator() }

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in MyList(listOf(2, 3))) s += x; return s }

fun box(): String {
    if (test() != 5) return "NOK"
    return "OK"
}
