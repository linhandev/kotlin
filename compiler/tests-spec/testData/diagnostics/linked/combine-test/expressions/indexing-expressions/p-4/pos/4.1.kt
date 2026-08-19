// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, indexing-expressions -> paragraph 4 -> sentence 4
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 4 -> sentence 4
 *                statements, assignments, simple-assignments -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: readonly List index read infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1() {
    checkSubtype<Int>(listOf(1, 2)[1])
}
