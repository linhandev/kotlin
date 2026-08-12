// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, statements, loop-statements, for-loop-statements -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: statements, loop-statements -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: break exits for loop early type inference
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun test(): Int { var s = 0; for (x in 1..10) { if (x == 4) break; s += x }; return s }

fun case1() {
    checkSubtype<Int>(test())
}
