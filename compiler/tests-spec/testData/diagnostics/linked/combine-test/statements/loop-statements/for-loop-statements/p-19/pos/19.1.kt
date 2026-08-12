// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: statements, loop-statements -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: labeled break@ exits outer for loop type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; loop@ for (i in 1..3) { for (j in 1..3) { if (j == 2) break@loop; s += j } }; return s }

fun case1() {
    checkSubtype<Int>(test())
}
