// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 6 -> sentence 6
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 6 -> sentence 6
 * NUMBER: 1
 * DESCRIPTION: empty Iterable does not execute loop body type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun test(): Int { var n = 0; for (x in emptyList<Int>()) n++; return n }

fun case1() {
    checkSubtype<Int>(test())
}
