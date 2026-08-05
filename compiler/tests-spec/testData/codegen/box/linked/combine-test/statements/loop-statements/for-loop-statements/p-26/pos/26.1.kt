// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 26 -> sentence 26
 *                operator-overloading, overview -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: 自定义 Iterator 每次迭代调用 next()
 */

// TESTCASE NUMBER: 1
class LogIt(private val it: Iterator<Int>) : Iterator<Int> { var nextCalls = 0; override fun hasNext(): Boolean = it.hasNext(); override fun next(): Int { nextCalls++; return it.next() } }

// TESTCASE NUMBER: 1
class Wrap(val data: List<Int>) : Iterable<Int> { val log = LogIt(data.iterator()); override fun iterator() = log }

// TESTCASE NUMBER: 1
fun test(): Int { val w = Wrap(listOf(10, 20)); var s = 0; for (x in w) s += x; return w.log.nextCalls }

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
