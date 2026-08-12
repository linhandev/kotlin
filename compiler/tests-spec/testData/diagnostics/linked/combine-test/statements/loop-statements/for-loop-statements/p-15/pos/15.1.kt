// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 15 -> sentence 15
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 15 -> sentence 15
 *                declarations, destructuring-declarations -> paragraph 15 -> sentence 15
 * NUMBER: 1
 * DESCRIPTION: for-in with destructuring over list of Pair type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for ((a, b) in listOf(1 to 2, 3 to 4)) s += a + b; return s }

fun case1() {
    checkSubtype<Int>(test())
}
