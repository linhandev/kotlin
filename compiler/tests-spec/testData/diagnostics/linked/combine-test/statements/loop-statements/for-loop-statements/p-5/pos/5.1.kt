// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 5 -> sentence 5
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 5 -> sentence 5
 *                expressions, range-expressions -> paragraph 5 -> sentence 5
 * NUMBER: 1
 * DESCRIPTION: downTo/until 区间可 for-in type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (i in 5 downTo 1) s += i; for (j in 0 until 3) s += j; return s }

fun case1() {
    checkSubtype<Int>(test())
}
