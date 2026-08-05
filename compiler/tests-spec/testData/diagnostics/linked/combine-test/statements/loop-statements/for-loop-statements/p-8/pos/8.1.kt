// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: Iterable 上 iterator() 只调用一次 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class C(val data: List<Int>) : Iterable<Int> { var iterCalls = 0; override fun iterator(): Iterator<Int> { iterCalls++; return data.iterator() } }

// TESTCASE NUMBER: 1
fun test(): Int { val c = C(listOf(1, 2)); for (x in c) { }; return c.iterCalls }

fun case1() {
    checkSubtype<Int>(test())
}
