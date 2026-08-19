// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 26 -> sentence 26
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 26 -> sentence 26
 *                statements, assignments, simple-assignments -> paragraph 26 -> sentence 26
 * NUMBER: 1
 * DESCRIPTION: indexed plusAssign then get infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val a = intArrayOf(1)
    a[0] += 2
    checkSubtype<Int>(a[0])
}
