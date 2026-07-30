// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 27 -> sentence 27
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 27 -> sentence 27
 *                statements, assignments, simple-assignments -> paragraph 27 -> sentence 27
 * NUMBER: 1
 * DESCRIPTION: indexed postfix ++ result and updated value infer Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    val a = intArrayOf(1)
    val old = a[0]++
    checkSubtype<Int>(old * 10 + a[0])
}
