// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: iterator() on Iterable is called only once
 */

// TESTCASE NUMBER: 1
class C(val data: List<Int>) : Iterable<Int> { var iterCalls = 0; override fun iterator(): Iterator<Int> { iterCalls++; return data.iterator() } }

// TESTCASE NUMBER: 1
fun test(): Int { val c = C(listOf(1, 2)); for (x in c) { }; return c.iterCalls }

fun box(): String {
    if (test() != 1) return "NOK"
    return "OK"
}
