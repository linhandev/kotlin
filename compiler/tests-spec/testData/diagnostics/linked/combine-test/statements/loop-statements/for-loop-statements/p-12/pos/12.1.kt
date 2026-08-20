// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 12 -> sentence 12
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 12 -> sentence 12
 * NUMBER: 1
 * DESCRIPTION: Iterator instance can be used directly in for-in type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in listOf(1, 2).iterator()) s += x; return s }

fun case1() {
    checkSubtype<Int>(test())
}
