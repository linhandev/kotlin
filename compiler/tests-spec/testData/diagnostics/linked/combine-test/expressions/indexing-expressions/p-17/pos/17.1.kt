// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 17 -> sentence 17
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 17 -> sentence 17
 *                statements, assignments, simple-assignments -> paragraph 17 -> sentence 17
 * NUMBER: 1
 * DESCRIPTION: Array.set returns Unit
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val a = arrayOf(1)
    a[0] = 2
    checkSubtype<Unit>(a.set(0, 3))
}
