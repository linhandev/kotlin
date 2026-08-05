// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 7 -> sentence 7
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 7 -> sentence 7
 *                operator-overloading, overview -> paragraph 7 -> sentence 7
 * NUMBER: 1
 * DESCRIPTION: for-in 右侧表达式只求值一次 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
var calls = 0

// TESTCASE NUMBER: 1
fun make(): List<Int> { calls++; return listOf(1) }

// TESTCASE NUMBER: 1
fun test(): Int { calls = 0; for (x in make()) { }; return calls }

fun case1() {
    checkSubtype<Int>(test())
}
