// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: 嵌套 for-in 可累加 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (row in listOf(listOf(1, 2), listOf(3))) for (x in row) s += x; return s }

fun case1() {
    checkSubtype<Int>(test())
}
