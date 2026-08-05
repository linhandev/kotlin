// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 16 -> sentence 16
 * PRIMARY LINKS: built-in-types-and-their-semantics, iterator-types -> paragraph 16 -> sentence 16
 * NUMBER: 1
 * DESCRIPTION: Sequence 可作为 for-in 对象 type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in sequenceOf(1, 2, 3)) s += x; return s }

fun case1() {
    checkSubtype<Int>(test())
}
