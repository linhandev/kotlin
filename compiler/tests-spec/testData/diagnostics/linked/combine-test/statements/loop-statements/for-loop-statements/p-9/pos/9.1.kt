// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 9 -> sentence 9
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 9 -> sentence 9
 * NUMBER: 1
 * DESCRIPTION: 自定义类实现 Iterable 可 for-in type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class MyList(private val data: List<Int>) : Iterable<Int> { override fun iterator(): Iterator<Int> = data.iterator() }

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in MyList(listOf(2, 3))) s += x; return s }

fun case1() {
    checkSubtype<Int>(test())
}
